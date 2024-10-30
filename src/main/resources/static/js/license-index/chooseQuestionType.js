function fetchSessions(category) {
    fetch(`/license/${category}`)
        .then(response => response.json())
        .then(sessions => {
            const sessionList = document.getElementById('session-list');
            sessionList.innerHTML = ''; // 기존 회차 리스트를 초기화

            // 새 회차 리스트 추가
            sessions.forEach(session => {
                const sessionDiv = document.createElement('div');
                sessionDiv.className = 'each-level';

                const input = document.createElement('input');
                input.type = 'radio';
                input.id = `session-${session.id}`;
                input.name = 'session';
                input.value = session.id;
                input.className = 'radio';
                input.onclick = () => requestSession(session.id); // 클릭 시 API 요청 함수 호출

                const label = document.createElement('label');
                label.setAttribute('for', `session-${session.id}`);
                label.className = 'check-box-text';
                label.innerText = session.content; // 회차 내용 표시

                sessionDiv.appendChild(input);
                sessionDiv.appendChild(label);
                sessionList.appendChild(sessionDiv);
            });
        });
}

function requestSession(sessionId) {
    window.location.href = `/question/license/${sessionId}`;
}