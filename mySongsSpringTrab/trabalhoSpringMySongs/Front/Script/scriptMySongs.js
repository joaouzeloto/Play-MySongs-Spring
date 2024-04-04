
function pesquisar() {
    var flag = 0;
    var tabela = document.getElementById("tabela-music");
    var pesquisa = document.getElementById("pesquisar");
    tabela.deleteRow(0);
    for (var i = 0; i < tabela.rows.length; i++) {
        var linha = tabela.rows[i];
        for (var j = 1; j < linha.cells.length - 1; j++) {
            var celula = linha.cells[j].toString();
            for (var k = 0, l = 0; k < celula.length; k++) {
                if (celula[k] == pesquisa[l]) {
                    l = 1;
                    for (; k < celula.length && l < pesquisa.length && [k] == pesquisa[l]; l++) {
                        if (pesquisa.length == l) {
                            flag = 1;
                        }
                    }
                }
            }
        }
        if (flag != 1) {
            tabela.deleteRow(i);
        }
        flag = 0;
    }
}

function cadastrarMusicas() {
    const URL = "http://localhost:8080/apis/add-musica-capa";
    var fdados = document.getElementById("fdados");
    var formData = new FormData(fdados); 

    fetch(URL, {
        method: 'POST',
        body: formData 
    })
    .then(resp => {
        return resp.text();
    })
    .then(text => {
        //alert(text);
        //carregaFilmes();
    })
    .catch(error => {
        console.error(error);
    });
}

function carregaMusicas()
{   const tag=document.getElementById("music-tabela-linhas");
    const URL="http://localhost:8080/apis/musicas";
    fetch(URL)
    .then(resp=>{
        return resp.json()
        .then(json=>{
            let lista="";
            for (let item of json)
            {
                lista+=`
                <tr style='width: 100%;' onmouseover="this.style.backgroundColor='#f5f5f5';" onmouseout="this.style.backgroundColor='transparent';">
                        <td style='width: 25%;'><img src="${item.urlCapa}" style='width: 48px; height: 48px;'></td>
                        <td style='width: 25%;'>${item.nome}</td>
                        <td style='width: 25%;'>${item.genero}</td>
                        <td style='width: 25%;'>${item.autor}</td>
                        <td style='width: 25%;'><audio controls loop><source src="${item.urlMusica}" type='audio/mpeg'></audio></td>
                </tr>`
                
            }
            tag.innerHTML=lista;
        })
    })
    .catch(Err=>{
        tag.innerText="Erro"+Err;
    })
}
