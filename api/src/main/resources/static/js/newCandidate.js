document.addEventListener("DOMContentLoaded", function () {

    const tipoUsuario = document.getElementById("typeUser");

    // Grupos de campos
    const grupoAluno = document.querySelectorAll(".grupo-aluno");
    const grupoRecrutador = document.querySelectorAll(".grupo-recrutador");
    const grupoEspecialista = document.querySelectorAll(".grupo-especialista");

    // Campos comuns
    const camposComuns = [
        "nome",
        "email",
        "senha",
        "cep",
        "logradouro",
        "numero",
        "complemento",
        "bairro",
        "estado-newUser"
    ];

    // Função para bloquear ou liberar um grupo
    function controlarGrupo(grupo, habilitar) {

        grupo.forEach(div => {

            // Esconde ou mostra o campo
            div.style.display = habilitar ? "" : "none";

            // Bloqueia ou libera os inputs
            const campos = div.querySelectorAll("input, select, textarea");

            campos.forEach(campo => {
                campo.disabled = !habilitar;
            });

        });

    }

    // Função para controlar os campos comuns
    function controlarCamposComuns(habilitar) {

        camposComuns.forEach(id => {

            const campo = document.getElementById(id);

            campo.disabled = !habilitar;

        });

    }

    // Inicialização do formulário
    function atualizarFormulario() {

        const tipo = tipoUsuario.value;

        // Bloqueia os campos comuns se nenhum usuário estiver selecionado
        controlarCamposComuns(tipo !== "");

        // Aluno e Ex-aluno
        controlarGrupo(
            grupoAluno,
            tipo === "aluno" || tipo === "exaluno"
        );

        // Recrutador
        controlarGrupo(
            grupoRecrutador,
            tipo === "recrutador"
        );

        // Especialista
        controlarGrupo(
            grupoEspecialista,
            tipo === "especialista"
        );

    }

    // Executa quando o usuário muda o combobox
    tipoUsuario.addEventListener("change", atualizarFormulario);

    // Executa ao abrir a página
    atualizarFormulario();

});
