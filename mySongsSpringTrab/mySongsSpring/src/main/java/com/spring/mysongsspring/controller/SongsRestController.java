package com.spring.mysongsspring.controller;

import com.spring.mysongsspring.entity.Musica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value="/apis")
public class SongsRestController implements WebMvcConfigurer {

    @GetMapping(value="/teste-conexao")
    public ResponseEntity<Object> testeConexao() throws IOException {
        File a = new File(getStaticPath());
        System.out.println(a.getName());
        return ResponseEntity.ok().body("getStaticPath()");
    }

    @PostMapping(value="/add-musica-capa")
    public ResponseEntity<Object> addMusicaCapa(@RequestParam("nome") String nome,
                                                @RequestParam("genero") String genero,
                                                @RequestParam("autor") String autor,
                                                @RequestParam("musica") MultipartFile musica,
                                                @RequestParam("capa") MultipartFile capa) {
        String fileNameImg= nome.replaceAll(" ","")+"_"+genero.replaceAll(" ", "") +"_"+autor.replaceAll(" ","")+".png";
        String fileNameMus = nome.replaceAll(" ","")+"_"+genero.replaceAll(" ", "") +"_"+autor.replaceAll(" ","")+".mp3";

        try {
            String caminho = getStaticPath().replace("target\\classes", "src\\main\\resources");

            File pastaMusicas = new File(caminho + "\\musicas");
            File pastaCapa = new File(caminho + "\\capa");

            Files.copy(musica.getInputStream(), Paths.get(pastaMusicas.getAbsolutePath()).
                    resolve(fileNameMus), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(capa.getInputStream(), Paths.get(pastaCapa.getAbsolutePath()).
                    resolve(fileNameImg), StandardCopyOption.REPLACE_EXISTING);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Erro ao receber o cartaz " + e.getMessage());
        }
        System.out.println(fileNameImg);
        return ResponseEntity.ok("inserido com sucesso");
    }

    @GetMapping("/musicas")
    public ResponseEntity<List<Musica>> getAllMusicas() {
        try {
            String caminho = getStaticPath().replace("target\\classes", "src\\main\\resources");
            File pastaMusicas = new File(caminho + "\\musicas");

            List<Musica> musicas = new ArrayList<>();

            if (pastaMusicas.exists() && pastaMusicas.isDirectory()) {
                File[] arquivosMusicas = pastaMusicas.listFiles();
                for (File arquivo : arquivosMusicas) {

                    String nomeArquivo = arquivo.getName();
                    String[] partesNome = nomeArquivo.split("_");
                    String nome = partesNome[0];
                    String genero = partesNome[1];
                    String autor = partesNome[2].split("\\.")[0]; // Remove a extensão do arquivo
                    String urlMusica = arquivo.getAbsolutePath(); // Caminho completo do arquivo de música
                    String urlCapa = arquivo.getAbsolutePath().replace(".mp3", ".png").replace("musicas", "capa");

                    // Construir e adicionar a instância de Musica à lista
                    Musica musica = new Musica(nome, genero, autor, urlMusica, urlCapa);
                    musicas.add(musica);
                }
            }

            return ResponseEntity.ok().body(musicas);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @Autowired
    private ResourceLoader resourceLoader;

    public String getStaticPath() throws IOException {
        String staticPath = null;
        staticPath = resourceLoader.getResource("classpath:static").getFile().getAbsolutePath();
        return staticPath;
    }

}
