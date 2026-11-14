const formChat = document.getElementById("formChat");

formChat.addEventListener("submit", function(event) {

    event.preventDefault();

    const campoMensagem = document.getElementById("mensagem");

    const texto = campoMensagem.value.trim();

    if (!texto) {
        return;
    }

    const chatMessages = document.getElementById("chatMessages");

    const novaMensagem = document.createElement("div");

    novaMensagem.classList.add("message", "sent");

    const textoMensagem = document.createElement("p");
    textoMensagem.textContent = texto;

    const remetente = document.createElement("span");
    remetente.textContent = "Você";

    novaMensagem.appendChild(textoMensagem);
    novaMensagem.appendChild(remetente);

    chatMessages.appendChild(novaMensagem);

    campoMensagem.value = "";

    chatMessages.scrollTop = chatMessages.scrollHeight;

});
