document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.descriptive-answer-btn').forEach(button => {
        button.addEventListener('click', async () => {
            const container = button.closest('.answer-input-container');
            const userAnswer = container.querySelector('.user-answer').value.trim();
            const aiResponseBox = button.closest('.each-question').querySelector('.ai-answer-container');
            const spinner = aiResponseBox.querySelector('.loading-spinner');
            const responseContent = aiResponseBox.querySelector('.ai-response-content');

            if (!userAnswer) return alert("답안을 입력하세요.");

            spinner.style.display = 'block';
            responseContent.textContent = '';

            try {
                const postResponse = await fetch(`/questions/major/descriptive/${button.dataset.questionId}/grade`, {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json'},
                    body: JSON.stringify({content: userAnswer}),
                });

                if (!postResponse.ok) throw new Error('답변 등록 실패');
                const key = await postResponse.text();

                const eventSource = new EventSource(`/chat/subscribe/${key}`);
                eventSource.onmessage = (event) => {
                    // JSON 문자열을 JavaScript 객체로 파싱
                    const parsedData = JSON.parse(event.data);
                    
                    const deltaContent = parsedData.firstChoiceDelta;

                    // 출력 및 포맷팅
                    console.log("firstChoiceDelta:", deltaContent);
                    formatAnswer(responseContent, deltaContent);
                };
                eventSource.onerror = (event) => {
                    eventSource.close();
                };
            } catch (error) {
                alert(`오류: ${error.message}`);
            } finally {
                spinner.style.display = 'none';
            }
        });
    });
});

function formatAnswer(element, newText) {
    // \n을 <br/>로 변환하여 기존 내용에 추가
    element.innerHTML += newText.replace(/\n/g, '<br/>');
}
