// const APIURLPI = 'http://localhost:8888';

const APIURLPI2 = 'http://localhost:8888';

async function saveUser(event) {

    event.preventDefault();

    console.log("Executado");

    try {

        const tipo = document.getElementById("typeUser").value;

        if (!tipo) {
            alert("Selecione o tipo de usuário.");
            return;
        }

        // DADOS COMUNS
        const usuario = {
            nome: document.getElementById("nome").value,
            email: document.getElementById("email").value,
            senha: document.getElementById("senha").value,
            tipo: tipo
        };

        // DADOS ESPECÍFICOS
        let dadosEspecificos = {};

        if (tipo === "aluno" || tipo === "exaluno") {

            dadosEspecificos = {
                curso: document.getElementById("selectCourseUser").value,
                instituicao: document.getElementById("instituicao").value,
                semestre: document.getElementById("selectSemestre").value,
                turno: document.getElementById("selectTurnoCourse").value,
                data_inicio: document.getElementById("dataInicio").value,
                data_termino: document.getElementById("dataFim").value
            };

        }

        else if (tipo === "recrutador") {

            dadosEspecificos = {
                razao_social: document.getElementById("razaoSocial").value,
                nome_fantasia: document.getElementById("nomeFantasia").value,
                cnpj: document.getElementById("cnpj").value,
                responsavel: document.getElementById("responsavel").value
            };

        }

        else if (tipo === "especialista") {

            dadosEspecificos = {
                formacao: document.getElementById("formacao").value
            };

        }

        // ENDEREÇO
        const endereco = {
            cep: document.getElementById("cep").value,
            logradouro: document.getElementById("logradouro").value,
            numero: document.getElementById("numero").value,
            complemento: document.getElementById("complemento").value,
            bairro: document.getElementById("bairro").value,
            cidade: document.getElementById('cidade').value,
            estado: document.getElementById("estado-newUser").value
        };

        // 1. CADASTRO DO USUÁRIO
        const resposta = await fetch(`${APIURLPI2}/login/cadastro`, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(usuario)

        });

        const dados = await resposta.json();

        if (!resposta.ok) {
            alert(dados.mensagem || "Erro ao cadastrar usuário.");
            return;
        }

        const id_usuario = dados.id_usuario;

        // 2. CADASTRO DOS DADOS ESPECÍFICOS
        const respostaPerfil = await fetch(`${APIURLPI2}/login/perfil`, {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                id_usuario,
                tipo,
                ...dadosEspecificos
            })

        });

        if (!respostaPerfil.ok) {
            throw new Error("Erro ao salvar os dados específicos.");
        }

        // 3. CADASTRO DO ENDEREÇO
        const respostaEndereco = await fetch(`${APIURLPI2}/login/endereco`, {

            method: "PUT",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                id_usuario,
                ...endereco
            })

        });

        if (!respostaEndereco.ok) {
            throw new Error("Erro ao salvar o endereço.");
        }

        alert("Cadastro realizado com sucesso!");

        window.location.href = "login.html";

    }

    catch (erro) {

        console.error("Erro:", erro);

        alert(erro.message || "Erro ao realizar cadastro.");

        alert("Não foi possível concluir o cadastro. Verifique a conexão com o servidor.");

    }

}