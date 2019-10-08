function criarAjax(recurso, funcao)
        {
        ajax = new XMLHttpRequest();
                ajax.onreadystatechange = funcao;
                ajax.open("GET", recurso, true);
                ajax.send();
                }
function mostrar()
        {
        if (this.readyState == 4 && this.status == 200)
        {
        doc = this.responseXML;
                raiz = doc.documentElement;
                texto = "";
                filhos =raiz.childNodes;
                tam = filhos.length;
                j = 1;
                for (i = 0; i < tam; i++)
        {
        if (filhos[i].nodeType == 1)
        {
        a = "buscaReceita?id=" + j
                texto += "<li>"+ filhos[i].nodeName+"<ul><li>"+ "<a onclick=criarAjax(" + '"' + a + '"' + ",pegaReceita)>" + filhos[i].firstChild.nodeValue + "</a></li></ul></li>";
                j += 2
        }
        }
        document.getElementById("ul").innerHTML = texto;
        }
        }

function pegaReceita(){
if (this.readyState == 4 && this.status == 200)
{document.getElementById("la").innerHTML = "";
doc = this.responseXML;
        raiz = doc.documentElement;
        texto = "";
        filhos = raiz.getElementsByTagName("ingrediente");
        tam = filhos.length;
        for (i = 0; i < tam; i++)
{
if (filhos[i].nodeType == 1)
{
texto += "<b>" + filhos[i].nodeName + "</b>:" + filhos[i].firstChild.nodeValue + "<br>";
}
}
filhos = raiz.getElementsByTagName("passo");
         tam = filhos.length;
        for ( i = 0; i < tam; i++)
{
if (filhos[i].nodeType == 1)
{
texto += "<b>" + filhos[i].nodeName + "</b>:" + filhos[i].firstChild.nodeValue + "<br>";
}
}
document.getElementById("la").innerHTML = texto;
}
}



