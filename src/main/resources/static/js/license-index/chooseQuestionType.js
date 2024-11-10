function fetchSessions(category) {
    fetch(`/license/${category}`)
        .then(response => response.json())
        .then(sessions => {
            const sessionList = document.getElementById('session-list');
            sessionList.innerHTML = ''; // 기존 회차 리스트 초기화

            // 새 회차 리스트 추가
            sessions.forEach(session => {
                const sessionDiv = document.createElement('div');
                sessionDiv.className = 'each-session';

                // 클릭 가능한 요소로 변경
                const sessionLabel = document.createElement('span');
                sessionLabel.className = 'check-box-text';
                sessionLabel.innerText = session.content; // 회차 내용 표시

                // 클릭 시 해당 회차로 이동
                sessionLabel.onclick = () => {
                    window.location.href = `/question/license/${session.id}`;
                };

                sessionDiv.appendChild(sessionLabel);
                sessionList.appendChild(sessionDiv);
            });
        });
}
