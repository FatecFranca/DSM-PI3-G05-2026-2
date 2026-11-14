//const APIURLPI = 'http://localhost:3000'

const APIURLPI = 'http://localhost:8888';

async function logout() {

    await fetch(`${APIURLPI}/login/logout`, {
        method: "POST",
        credentials: "include"
    });

    window.location.href = "login.html";
}