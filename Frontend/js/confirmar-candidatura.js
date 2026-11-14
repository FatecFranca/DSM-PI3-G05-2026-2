const APIURLPI = 'http://localhost:8888'

async function confirmarCandidatura() {

    const parametros = new URLSearchParams(window.location.search);

    const id_vaga = parametros.get("id");

    if (!id_vaga) {
        alert("Vaga não encontrada.");
        return;
    }

    try {

        const resposta = await fetch(
            `${APIURLPI}/candidaturas`,
            {
                method: "POST",
                credentials: "include",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    id_vaga: id_vaga
                })
            }
        );

        const dados = await resposta.json();

        if (!resposta.ok) {
            alert(dados.mensagem || "Não foi possível realizar a candidatura.");
            return;
        }

        alert("Candidatura realizada com sucesso!");

        window.location.href = "minhas-candidaturas.html";

    } catch (erro) {

        console.error(erro);

        alert("Não foi possível concluir a candidatura. Verifique sua conexão.");

    }
}

document.getElementById("btnConfirmar")
    .addEventListener("click", confirmarCandidatura);
