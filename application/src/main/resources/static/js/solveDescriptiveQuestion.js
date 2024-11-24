document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.descriptive-answer-btn').forEach(button => {
        button.addEventListener('click', function () {
            // 버튼이 포함된 부모 컨테이너에서 textarea 요소를 찾아 값 가져오기
            const answerInputContainer = this.closest('.answer-input-container');
            const userAnswer = answerInputContainer.querySelector('.user-answer').value.trim(); // textarea 값 가져오기
            const aiResponseBox = this.closest('.each-question').querySelector('.ai-answer-container');

            if (!userAnswer || userAnswer.length === 0) {
                alert("답안을 입력하세요.");
                return;
            }

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
                    aiResponseBox.textContent = data;
                })
                .catch(error => {
                    alert(`오류 발생: ${error.message}`);
                });
        });
    });
});
