//const APIURLPI = 'http://localhost:3000'

const APIURLPI = 'http://localhost:8888';

async function fazerLogin() {

    const email = document.getElementById("userEmail").value;
    const senha = document.getElementById("password").value;

    try {

        const resposta = await fetch(
            `${APIURLPI}/login`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                credentials: "include",
                body: JSON.stringify({
                    email,
                    senha
                })
            }
        );

        const dados = await resposta.json();

        console.log("STATUS:", resposta.status);
        console.log("DADOS:", dados);

        if (resposta.ok) {

            alert("Login OK")

            setTimeout(() => {
                window.location.href = "area-logada.html";
            }, 150);

        } else {

            alert("Email ou senha inválidos!!");
        }

    } catch (erro) {

        console.error(erro);
        alert("Erro ao conectar ao servidor");
    }
}

function newuser() {
    window.location.href = "../../pages/newcandidate.html"
}

function forgotpsw() {
    window.location.href = "../../pages/nova-senha.html";
}