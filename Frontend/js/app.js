// const APIURLPI = 'http://localhost:3000'

const APIURLPI = 'http://localhost:8888';

window.addEventListener("DOMContentLoaded", async () => {

    const btnLogin = document.getElementById("btnLogin");
    const btnPerfil = document.getElementById("btnPerfil");
    const btnSair = document.getElementById("btnSair");

    // Estado inicial: usuário não autenticado
    btnLogin.style.display = "block";
    btnPerfil.style.display = "none";
    btnSair.style.display = "none";

    try {

        const response = await fetch(`${APIURLPI}/login`, {
            credentials: "include"
        });

        if (response.ok) {

            const data = await response.json();

            btnLogin.style.display = "none";
            btnPerfil.style.display = "block";
            btnSair.style.display = "block";

            console.log(data);

        } else {

            btnLogin.style.display = "block";
            btnPerfil.style.display = "none";
            btnSair.style.display = "none";
        }

    } catch (error) {

        console.error(error);

        btnLogin.style.display = "block";
        btnPerfil.style.display = "none";
        btnSair.style.display = "none";
    }
});
