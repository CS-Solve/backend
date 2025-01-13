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
                    responseContent.textContent += event.data
                    formatSpecificQuestion(responseContent);
                };
                eventSource.onerror = () => {
                    alert('등록되지 않은 답변입니다.');
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

function formatSpecificQuestion(element) {
    let lines = element.textContent.split('\n');

    let formattedLines = lines.map(line => {
        let formattedLine = line;

        if (formattedLine.startsWith('# ')) {
            formattedLine = formattedLine.replace(/^# (.*)/, '<span style="font-weight: bold; font-size: 1.6em;">$1</span>');
        } else if (formattedLine.startsWith('## ')) {
            formattedLine = formattedLine.replace(/^## (.*)/, '<span style="font-weight: bold; font-size: 1.4em;">$1</span>');
        } else if (formattedLine.startsWith('### ')) {
            formattedLine = formattedLine.replace(/^### (.*)/, '<span style="font-weight: bold; font-size: 1.2em;">$1</span>');
        }

        formattedLine = formattedLine.replace(/\*\*(.*?)\*\*/g, '<span style="font-weight: bold; font-size: 1em;">$1</span>');
        return formattedLine;
    });

    element.innerHTML = formattedLines.join('<br/>');
}
