document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.descriptive-answer-btn').forEach(button => {
        button.addEventListener('click', function () {
            const answerInputContainer = this.closest('.answer-input-container');
            const userAnswer = answerInputContainer.querySelector('.user-answer').value.trim(); // textarea 값 가져오기
            const aiResponseBox = this.closest('.each-question').querySelector('.ai-answer-container');
            const loadingSpinner = aiResponseBox.querySelector('.loading-spinner'); // 로딩 스피너
            const aiResponseContent = aiResponseBox.querySelector('.ai-response-content'); // AI 답변 컨텐츠

            if (!userAnswer || userAnswer.length === 0) {
                alert("답안을 입력하세요.");
                return;
            }

            // 로딩 시작
            loadingSpinner.style.display = 'block';
            aiResponseContent.textContent = ''; // 이전 응답 초기화

            fetch(`/questions/descriptive/${this.getAttribute('data-question-id')}/grade`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({content: userAnswer}),
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('AI 답변 생성 실패');
                    }
                    return response.text();
                })
                .then(data => {
                    aiResponseContent.textContent = data;
                    formatSpecificQuestion(aiResponseContent);
                })
                .catch(error => {
                    alert(`오류 발생: ${error.message}`);
                })
                .finally(() => {
                    // 로딩 끝
                    loadingSpinner.style.display = 'none';
                });
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
