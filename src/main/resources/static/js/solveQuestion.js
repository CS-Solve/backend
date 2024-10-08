document.addEventListener('DOMContentLoaded', function() {
    let selectedChoice = null;
    const choices = document.querySelectorAll('.choice-item');
    const checkAnswerButton = document.getElementById('checkButton');
    const answerBox = document.getElementById('answerBox');
    const answerText = document.getElementById('answerText');
    const descriptionBox = document.querySelector('.description-box');
    const toggleButton = document.getElementById("toggleDescription");
    const descriptionContent = document.getElementById("descriptionContent");
    const descriptionText = document.getElementById("descriptionText");

    // 사이드바 관련 요소
    const totalQuestionsElement = document.getElementById('totalQuestions');
    const correctQuestionsElement = document.getElementById('correctQuestions');
    const incorrectQuestionsElement = document.getElementById('incorrectQuestions');
    const questionList = document.getElementById('questionList');

    let totalQuestions = document.querySelectorAll('.question-box').length;
    let correctQuestions = 0;
    let incorrectQuestions = 0;

    // 각 문제에 대한 채점 여부 저장
    const questionResults = new Array(totalQuestions).fill(null); // null은 아직 채점되지 않음, true/false로 맞음/틀림 저장

    // 총 문제 수 업데이트
    totalQuestionsElement.textContent = totalQuestions;

    // 문제 리스트 생성
    document.querySelectorAll('.question-box').forEach((box, index) => {
        const link = document.createElement('a');
        link.href = `#question-${index + 1}`;
        link.textContent = `문제 ${index + 1}`;
        link.dataset.questionIndex = index;

        // 호버 효과 추가
        link.addEventListener('mouseenter', function() {
            this.style.backgroundColor = '#e0e0e0';
            this.style.fontWeight = 'bold';
            const questionBox = document.querySelectorAll('.question-box')[index];
            if (questionBox) {
                questionBox.style.backgroundColor = '#f0f0f0';
            }
        });

        link.addEventListener('mouseleave', function() {
            this.style.backgroundColor = '';
            this.style.fontWeight = 'normal';
            const questionBox = document.querySelectorAll('.question-box')[index];
            if (questionBox) {
                questionBox.style.backgroundColor = '';
            }
        });

        questionList.appendChild(link);
    });

    // 이벤트 위임을 사용하여 문제 클릭 처리
    questionList.addEventListener(
        'click', function(e) {
            if (e.target.tagName === 'A') {
                e.preventDefault();
                const index = e.target.dataset.questionIndex;
                const questionBox = document.querySelectorAll('.question-box')[index];
                if (questionBox) {
                    questionBox.scrollIntoView({ behavior: 'smooth' ,block: 'start'});
                }
            }
        });

    // 선택지 클릭 이벤트 (이벤트 위임 사용)
    document.querySelector('.left-box').addEventListener('click', function(e) {
        if (e.target.classList.contains('choice-item')) {
            if (selectedChoice) {
                selectedChoice.classList.remove('selected-choice');
            }
            selectedChoice = e.target;
            selectedChoice.classList.add('selected-choice');
        }
    });

    // 채점 버튼 클릭 이벤트
    checkAnswerButton.addEventListener('click', function() {
        if (selectedChoice) {
            const questionBox = selectedChoice.closest('.question-box');
            const questionIndex = Array.from(document.querySelectorAll('.question-box')).indexOf(questionBox);


            const isCorrect = selectedChoice.getAttribute('data-answer-status') === 'true';
            const selectText = selectedChoice.getAttribute('data-choice-text');

            // 정답 여부에 따라 결과 표시
            if (isCorrect) {
                answerBox.style.display = 'flex';
                answerBox.classList.add('correct-answer');
                answerBox.classList.remove('wrong-answer');
                answerText.textContent = '정답입니다! (' + selectText + ")";
                if(questionResults[questionIndex]===null){
                    correctQuestions++;
                }
                if(questionResults[questionIndex]===false){
                    correctQuestions++;
                    incorrectQuestions--;
                }
                questionResults[questionIndex] = true; // 이 문제는 맞았다고 기록
            } else {
                answerBox.style.display = 'flex';
                answerBox.classList.add('wrong-answer');
                answerBox.classList.remove('correct-answer');
                answerText.textContent = '오답입니다! (' + selectText + ")";
                if(questionResults[questionIndex]===null){
                    incorrectQuestions++;
                }
                if(questionResults[questionIndex]===true){
                    correctQuestions--;
                    incorrectQuestions++;
                }
                questionResults[questionIndex] = false; // 이 문제는 틀렸다고 기록
            }

            // 사이드바 업데이트
            updateSidebar(selectedChoice, isCorrect);

            // 해설 보기 버튼 표시
            toggleButton.style.display = "block";
            descriptionBox.style.display = "block";

            // 선택된 질문의 설명을 가져와서 descriptionBox에 표시
            const description = selectedChoice.closest('.each-question').getAttribute('data-description');
            descriptionText.textContent = description;
        } else {
            answerBox.style.display = 'flex';
            answerText.textContent = '선택지를 먼저 선택하세요!';
            answerBox.classList.add('wrong-answer');
            answerBox.classList.remove('correct-answer');
        }
    });

    // 해설 보기 버튼 클릭 이벤트
    toggleButton.addEventListener("click", function() {
        if (descriptionContent.style.display === "none") {
            descriptionContent.style.display = "block";
            toggleButton.textContent = "해설 숨기기 ▲";
        } else {
            descriptionContent.style.display = "none";
            toggleButton.textContent = "해설 보기 ▼";
        }
    });

    // 사이드바 업데이트 함수
    function updateSidebar(selectedChoice, isCorrect) {
        const questionBox = selectedChoice.closest('.question-box');
        const questionIndex = Array.from(document.querySelectorAll('.question-box')).indexOf(questionBox);
        const link = questionList.children[questionIndex];

        if (isCorrect) {
            link.style.color = 'green';
            link.style.borderLeft = '3px solid green';
        } else {
            link.style.color = 'red';
            link.style.borderLeft = '3px solid red';
        }

        correctQuestionsElement.textContent = correctQuestions;
        incorrectQuestionsElement.textContent = incorrectQuestions;
    }
});