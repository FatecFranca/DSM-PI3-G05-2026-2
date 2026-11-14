window.addEventListener("DOMContentLoaded", () => {

    const listaVagas = document.getElementById("listaVagas");
    const pesquisarVaga = document.getElementById("pesquisarVaga");
    const filtroLocal = document.getElementById("filtroLocal");

    let vagas = [];

    // Busca as vagas no backend
    async function carregarVagas() {

        try {

            const resposta = await fetch(`${APIURLPI}/vagas`);

            if (!resposta.ok) {
                throw new Error("Não foi possível carregar as vagas.");
            }

            vagas = await resposta.json();

            exibirVagas(vagas);

        } catch (erro) {

            console.error("Erro ao carregar vagas:", erro);

            listaVagas.textContent =
                "Não foi possível carregar as vagas. Tente novamente mais tarde.";

        }
    }

    // Exibe as vagas na página
    function exibirVagas(lista) {

        listaVagas.innerHTML = "";

        if (lista.length === 0) {

            listaVagas.textContent = "Nenhuma vaga encontrada.";

            return;
        }

        lista.forEach(vaga => {

            const card = document.createElement("article");
            card.classList.add("job-card");

            const titulo = document.createElement("h2");
            titulo.textContent = vaga.titulo;

            const empresa = document.createElement("p");
            empresa.classList.add("meta");

            const nomeEmpresa = document.createElement("strong");
            nomeEmpresa.textContent = "Empresa: ";

            empresa.append(nomeEmpresa, vaga.empresa);

            const informacoes = document.createElement("ul");
            informacoes.classList.add("job-list");

            const local = document.createElement("li");
            local.textContent = `📍 ${vaga.local} · ${vaga.nivel}`;

            const salario = document.createElement("li");
            salario.textContent = `💰 ${vaga.salario}`;

            const requisitos = document.createElement("li");
            requisitos.textContent = `🧠 Requisitos: ${vaga.requisitos}`;

            informacoes.append(local, salario, requisitos);

            const candidatura = document.createElement("a");
            candidatura.classList.add("apply");
            candidatura.href = "#";
            candidatura.textContent = "Candidatar-se";

            card.append(
                titulo,
                empresa,
                informacoes,
                candidatura
            );

            listaVagas.appendChild(card);

        });

    }

    // Filtros de pesquisa
    function filtrarVagas() {

        const pesquisa = pesquisarVaga.value.toLowerCase();
        const localSelecionado = filtroLocal.value.toLowerCase();

        const resultado = vagas.filter(vaga => {

            const correspondePesquisa =
                vaga.titulo.toLowerCase().includes(pesquisa) ||
                vaga.empresa.toLowerCase().includes(pesquisa);

            const correspondeLocal =
                !localSelecionado ||
                vaga.local.toLowerCase().includes(localSelecionado);

            return correspondePesquisa && correspondeLocal;

        });

        exibirVagas(resultado);
    }

    pesquisarVaga.addEventListener("input", filtrarVagas);
    filtroLocal.addEventListener("change", filtrarVagas);

    carregarVagas();

});
