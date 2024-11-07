document.addEventListener("DOMContentLoaded", function () {
    const input = document.getElementById("chatbotInput");

    // 엔터 키 이벤트 처리
    input.addEventListener("keypress", function (event) {
        if (event.key === "Enter" && !event.shiftKey) {
            event.preventDefault(); // 기본 엔터 동작 방지
            sendMessage();
        }
    });
});

// API 요청 및 메시지 추가 함수
function sendMessage() {
    const input = document.getElementById('chatbotInput');
    const messageText = input.value.trim();
    if (!messageText) return;

    addMessageToChat("user", messageText);
    input.value = "";

    // /chat/text에 질문을 API 요청으로 보냅니다.
    fetch('/chat/text', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({prompt: messageText})
    })
        .then(response => response.json())
        .then(data => {
            addMessageToChat("bot", data.content); // 응답의 content를 추가
        })
        .catch(error => {
            console.error("Error:", error);
        });
}

// 채팅 메시지 추가 함수
function addMessageToChat(role, text) {
    const messageContainer = document.getElementById('chatbotMessages');
    const message = document.createElement("div");
    message.classList.add("message", role);
    message.innerText = text;
    messageContainer.appendChild(message);
    messageContainer.scrollTop = messageContainer.scrollHeight; // 최신 메시지로 스크롤
}
