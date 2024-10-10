document.addEventListener('DOMContentLoaded', function () {
    let selectedChoice = null;
    let selectedQuestion = null;
    let currentQuestionIndex;
    let marked = false;

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

    function registerEventListeners() {
        questionList.addEventListener('click', handleQuestionListClick);
        document.querySelector('.left-box').addEventListener('click', handleQuestionClick);
        checkAnswerButton.addEventListener('click', handleCheckAnswer);
        toggleButton.addEventListener('click', toggleDescriptionVisibility);
    }

    function initialize() {
        // 총 문제 수 업데이트
        totalQuestionsElement.textContent = totalQuestions;

        // 사이드바 문제 리스트 생성
        createQuestionList();

        // 이벤트 리스너 등록
        registerEventListeners();
    }

    /*
    사이드 바 생성
     */
    function createQuestionList() {
        const categories = document.querySelectorAll('.eachCategory');
        const questionList = document.getElementById('questionList'); // questionList는 여기에 질문 링크를 추가하는 요소
        questionList.innerHTML = ''; // 기존 리스트 초기화

        categories.forEach((category, categoryIndex) => {
            // 카테고리 제목 가져오기
            const categoryText = category.querySelector('.category-text').textContent;

            // questionBarCategory div 생성
            const questionBarCategory = document.createElement('div');
            questionBarCategory.classList.add('questionBarCategory');

            // categoryTextBox div 생성 (텍스트만을 위한 div)
            const questionBarCategoryTitleBox = document.createElement('div');
            questionBarCategoryTitleBox.textContent = categoryText;
            questionBarCategoryTitleBox.classList.add('questionBarCategoryTitleBox'); // 텍스트를 위한 추가 스타일링 클래스

            // categoryTextBox를 questionBarCategory 안에 추가
            questionBarCategory.appendChild(questionBarCategoryTitleBox);

            // 해당 카테고리 안의 질문들을 처리
            const questions = category.querySelectorAll('.question-box');
            questions.forEach((box, index) => {
                const link = document.createElement('a');
                link.href = `#question-${index}`;
                link.textContent = `문제 ${index + 1}`;
                link.dataset.questionIndex = index;

                // addHoverEffectToLink 함수 호출로 링크에 호버 효과 추가
                addHoverEffectToLink(link, index);

                // 각 질문 링크를 카테고리 안에 추가
                questionBarCategory.appendChild(link);
            });

            // 완성된 카테고리를 questionList에 추가
            questionList.appendChild(questionBarCategory);
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



    function handleQuestionListClick(e) {
        if (e.target.tagName === 'A') {
            e.preventDefault();

            // 클릭한 a 태그의 카테고리 부모를 찾음
            const questionBarCategory = e.target.closest('.questionBarCategory');

            // 자식 요소인 제목(텍스트)를 가져옴
            const questionBarCategoryTitle = questionBarCategory.querySelector('.questionBarCategoryTitleBox').textContent.trim(); // 제목 텍스트를 가져옴
            const index = e.target.dataset.questionIndex; // 카테고리 내에서 a 태그의 인덱스를 가져옴

            // 실제 카테고리들을 찾음
            const categories = document.querySelectorAll(".eachCategory");
            let matchedCategory = null;


            // 같은 텍스트를 가진 카테고리를 찾음
            categories.forEach((category) => {
                const categoryText = category.querySelector('.category-text').textContent.trim();
                if (categoryText === questionBarCategoryTitle) {
                    matchedCategory = category;
                }
            });
            if (matchedCategory) {
                // 찾은 카테고리 내에서 해당 질문 요소 찾기
                const questionBoxes = matchedCategory.querySelectorAll(".question-box");
                const questionBox = questionBoxes[index];
                // 해당 질문으로 스크롤
                if (questionBox) {
                    questionBox.scrollIntoView({ behavior: 'smooth', block: 'start' });
                } else {
                    console.error('해당 질문을 찾을 수 없습니다.');
                }
            } else {
                console.error('해당 카테고리를 찾을 수 없습니다.');
            }
        }
    }

    function handleQuestionClick(e) {
        // 해설 내용을 바꾼다.
        if (e.target.classList.contains('choice-item')) {
            selectChoice(e.target);
            /*
            객관식 문제가 아닐때만 문제 선택을 활성화 한다.
             */
        } else if (multipleChoice ===false && e.target.classList.contains('questionItem')) {
            selectQuestion(e.target);
        }
    }

    function selectChoice(choice) {
        if (selectedChoice) {
            selectedChoice.classList.remove('selected-choice');
        }
        selectedChoice = choice;
        selectedChoice.classList.add('selected-choice');
        refreshAnswerAndDescription();
    }

    function selectQuestion(question) {
        if (selectedQuestion) {
            selectedQuestion.classList.remove('selected-question');
        }
        selectedQuestion = question;
        selectedQuestion.classList.add('selected-question');
       refreshAnswerAndDescription();
    }
    function refreshAnswerAndDescription(){
        makeDescriptionForHtml()
        hideAnswerMessage();
        hideDescription();
        marked = false;
    }

    function toggleDescriptionVisibility() {
        if(marked === false){

        }
        if (descriptionContent.style.display === "none") {
            showDescription();
        } else {
            hideDescription();
        }
    }

    /**
     * 채점 버튼을 눌렀을시
     */
    function handleCheckAnswer() {
        if (selectedChoice && multipleChoice) {
            processMultipleChoice();
        } else if (!multipleChoice && selectedQuestion) {
            processShortAnswer();
        } else {
            showAnswerMessage('먼저 선택하세요!', false);
        }
        marked = true;
    }

    function processMultipleChoice() {
        const questionBox = selectedChoice.closest('.question-box');
        const questionIndex = Array.from(document.querySelectorAll('.question-box')).indexOf(questionBox);
        const isCorrect = selectedChoice.getAttribute('data-answer-status') === 'true';
        const selectText = selectedChoice.getAttribute('data-choice-text');

        updateAnswerResult(isCorrect, selectText, questionIndex);
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
    function hideAnswerMessage(){
        answerBox.style.display='none';
    }

    function makeDescriptionForHtml() {
        /*
        주관식이냐, 객관식이냐에 출력 내용을 구분한다.
         */
        const description = selectedChoice ? selectedChoice.closest('.each-question').getAttribute('data-description') : selectedQuestion.closest('.each-question').getAttribute('data-description');
        descriptionText.innerHTML = description.replace(/\n/g, '<br/>');
    }

    function updateSidebar(element, isCorrect) {
        // 현재 질문이 속한 카테고리를 찾음
        const questionBox = element.closest('.question-box'); // 현재 질문을 찾음
        const category = element.closest('.eachCategory'); // 해당 질문이 속한 카테고리 찾기

        // 카테고리 내에서 질문들의 리스트를 가져옴
        const questionBoxes = Array.from(category.querySelectorAll('.question-box')); // NodeList를 배열로 변환
        const questionIndex = questionBoxes.indexOf(questionBox); // 배열에서 현재 질문의 인덱스를 찾음

        const categoryText = category.querySelector('.category-text').textContent.trim(); // 카테고리 이름 가져오기
        console.log('카테고리:', categoryText, '질문 인덱스:', questionIndex);

        // 사이드바에서 해당 카테고리 이름을 가진 요소를 찾음
        const questionBarCategories = questionList.querySelectorAll('.questionBarCategory'); // 모든 사이드바 카테고리
        let matchedSidebarCategory = null;

        questionBarCategories.forEach((questionBarCategory) => {
            const sidebarCategoryText = questionBarCategory.querySelector('.questionBarCategoryTitleBox').textContent.trim();
            if (sidebarCategoryText === categoryText) {
                matchedSidebarCategory = questionBarCategory; // 카테고리 이름이 같은 사이드바 카테고리를 찾음
            }
        });

        if (matchedSidebarCategory) {
            // 해당 카테고리 내에서 질문 링크를 찾음
            const links = matchedSidebarCategory.querySelectorAll('a'); // 해당 카테고리 안의 모든 링크를 가져옴
            const link = links[questionIndex]; // 해당 질문 인덱스의 링크 선택
            if (link) {
                // 링크의 색상과 스타일을 업데이트
                link.style.color = isCorrect ? 'green' : 'red';
                link.style.borderLeft = isCorrect ? '3px solid green' : '3px solid red';
            } else {
                console.error('해당 인덱스의 링크를 찾을 수 없습니다.');
            }
        } else {
            console.error('해당 카테고리를 찾을 수 없습니다.');
        }

        // 사이드바의 맞은 문제/틀린 문제 업데이트
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