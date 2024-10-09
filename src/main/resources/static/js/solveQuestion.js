document.addEventListener('DOMContentLoaded', function () {
    let selectedChoice = null;
    let selectedQuestion = null;

    // 상수 정의
    const choices = document.querySelectorAll('.choice-item');
    const checkAnswerButton = document.getElementById('checkButton');
    const answerBox = document.getElementById('answerBox');
    const answerText = document.getElementById('answerText');
    const descriptionBox = document.querySelector('.description-box');
    const toggleButton = document.getElementById("toggleDescription");
    const descriptionContent = document.getElementById("descriptionContent");
    const descriptionText = document.getElementById("descriptionText");
    const totalQuestionsElement = document.getElementById('totalQuestions');
    const correctQuestionsElement = document.getElementById('correctQuestions');
    const incorrectQuestionsElement = document.getElementById('incorrectQuestions');
    const questionList = document.getElementById('questionList');

    const totalQuestions = document.querySelectorAll('.question-box').length;
    const multipleChoice = document.getElementById('multipleChoice').value === 'true';
    let correctQuestions = 0;
    let incorrectQuestions = 0;
    const questionResults = new Array(totalQuestions).fill(null);

    // 초기화 작업
    initialize();

    function initialize() {
        // 총 문제 수 업데이트
        totalQuestionsElement.textContent = totalQuestions;

        // 문제 리스트 생성
        createQuestionList();

        // 이벤트 리스너 등록
        registerEventListeners();
    }

    function createQuestionList() {
        document.querySelectorAll('.question-box').forEach((box, index) => {
            const link = document.createElement('a');
            link.href = `#question-${index + 1}`;
            link.textContent = `문제 ${index + 1}`;
            link.dataset.questionIndex = index;
            addHoverEffectToLink(link, index);
            questionList.appendChild(link);
        });
    }

    function addHoverEffectToLink(link, index) {
        link.addEventListener('mouseenter', function () {
            this.style.backgroundColor = '#e0e0e0';
            this.style.fontWeight = 'bold';
            highlightQuestionBox(index, true);
        });
        link.addEventListener('mouseleave', function () {
            this.style.backgroundColor = '';
            this.style.fontWeight = 'normal';
            highlightQuestionBox(index, false);
        });
    }

    function highlightQuestionBox(index, highlight) {
        const questionBox = document.querySelectorAll('.question-box')[index];
        if (questionBox) {
            questionBox.style.backgroundColor = highlight ? '#f0f0f0' : '';
        }
    }

    function registerEventListeners() {
        questionList.addEventListener('click', handleQuestionListClick);
        document.querySelector('.left-box').addEventListener('click', handleQuestionClick);
        checkAnswerButton.addEventListener('click', handleCheckAnswer);
        toggleButton.addEventListener('click', toggleDescriptionVisibility);
    }

    function handleQuestionListClick(e) {
        if (e.target.tagName === 'A') {
            e.preventDefault();
            const index = e.target.dataset.questionIndex;
            const questionBox = document.querySelectorAll('.question-box')[index];
            if (questionBox) {
                questionBox.scrollIntoView({ behavior: 'smooth', block: 'start' });
            }
            hideDescription();
        }
    }

    function handleQuestionClick(e) {
        if (e.target.classList.contains('choice-item')) {
            selectChoice(e.target);
        } else if (e.target.classList.contains('question-item')) {
            selectQuestion(e.target);
        }
    }

    function selectChoice(choice) {
        if (selectedChoice) {
            selectedChoice.classList.remove('selected-choice');
        }
        selectedChoice = choice;
        selectedChoice.classList.add('selected-choice');
        hideDescription();
    }

    function selectQuestion(question) {
        if (selectedQuestion) {
            selectedQuestion.classList.remove('selected-question');
        }
        selectedQuestion = question;
        selectedQuestion.classList.add('selected-question');
        hideDescription();
    }

    function toggleDescriptionVisibility() {
        if (descriptionContent.style.display === "none") {
            showDescription();
        } else {
            hideDescription();
        }
    }

    function handleCheckAnswer() {
        if (selectedChoice && multipleChoice) {
            processMultipleChoice();
        } else if (!multipleChoice && selectedQuestion) {
            processShortAnswer();
        } else {
            showAnswerMessage('선택지를 먼저 선택하세요!', false);
        }
    }

    function processMultipleChoice() {
        const questionBox = selectedChoice.closest('.question-box');
        const questionIndex = Array.from(document.querySelectorAll('.question-box')).indexOf(questionBox);
        const isCorrect = selectedChoice.getAttribute('data-answer-status') === 'true';
        const selectText = selectedChoice.getAttribute('data-choice-text');

        updateAnswerResult(isCorrect, selectText, questionIndex);
        makeDescriptionForHtml();
        updateSidebar(selectedChoice, isCorrect);
    }

    function processShortAnswer() {
        const questionBox = selectedQuestion.closest('.question-box');
        const questionIndex = Array.from(document.querySelectorAll('.question-box')).indexOf(questionBox);

        showAnswerMessage('주관식 문제는 해설이 바로 표시됩니다.', true);
        if (questionResults[questionIndex] === null) {
            correctQuestions++;
        }
        questionResults[questionIndex] = true;
        updateSidebar(selectedQuestion, true);
        showDescription();
        makeDescriptionForHtml();
    }

    function updateAnswerResult(isCorrect, selectText, questionIndex) {
        showAnswerMessage(isCorrect ? `정답입니다! (${selectText})` : `오답입니다! (${selectText})`, isCorrect);

        if (isCorrect) {
            if (questionResults[questionIndex] === null) correctQuestions++;
            if (questionResults[questionIndex] === false) {
                correctQuestions++;
                incorrectQuestions--;
            }
        } else {
            if (questionResults[questionIndex] === null) incorrectQuestions++;
            if (questionResults[questionIndex] === true) {
                correctQuestions--;
                incorrectQuestions++;
            }
        }

        questionResults[questionIndex] = isCorrect;
    }

    function showAnswerMessage(message, isCorrect) {
        answerBox.style.display = 'flex';
        answerBox.classList.toggle('correct-answer', isCorrect);
        answerBox.classList.toggle('wrong-answer', !isCorrect);
        answerText.textContent = message;
    }

    function makeDescriptionForHtml() {
        const description = selectedChoice ? selectedChoice.closest('.each-question').getAttribute('data-description') : selectedQuestion.closest('.each-question').getAttribute('data-description');
        descriptionText.innerHTML = description.replace(/\n/g, '<br/>');
    }

    function updateSidebar(element, isCorrect) {
        const questionBox = element.closest('.question-box');
        const questionIndex = Array.from(document.querySelectorAll('.question-box')).indexOf(questionBox);
        const link = questionList.children[questionIndex];

        link.style.color = isCorrect ? 'green' : 'red';
        link.style.borderLeft = isCorrect ? '3px solid green' : '3px solid red';

        correctQuestionsElement.textContent = correctQuestions;
        incorrectQuestionsElement.textContent = incorrectQuestions;
    }

    function showDescription() {
        descriptionContent.style.display = "block";
        toggleButton.textContent = "해설 숨기기 ▲";
    }

    function hideDescription() {
        descriptionContent.style.display = "none";
        toggleButton.textContent = "해설 보기 ▼";
    }
});