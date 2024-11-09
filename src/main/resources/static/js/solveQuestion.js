document.addEventListener('DOMContentLoaded', function () {
    let selectedChoice = null;
    let selectedQuestion = null;
    let marked = false;
    // 상수 정의
    const checkAnswerButton = document.getElementById('checkButton');
    const answerBox = document.getElementById('answerBox');
    const answerText = document.getElementById('answerText');
    const toggleButton = document.getElementById("toggleDescription");
    const descriptionContent = document.getElementById("descriptionContent");
    const descriptionText = document.getElementById("descriptionText");
    const totalQuestionsCount = document.getElementById('totalQuestionsCount');
    const questionList = document.getElementById('questionList');

    const totalQuestions = document.querySelectorAll('.question-box');
    const multipleChoice = document.getElementById('multipleChoice').value === 'true';
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
        totalQuestionsCount.textContent = totalQuestions.length;

        // 사이드바 문제 리스트 생성
        createQuestionList();

        // 이벤트 리스너 등록
        registerEventListeners();
        // 세션 정보에 저장되어 있는 것들을 적용
        applyDataFromSessionStorage()
    }

    function getStorageKey(category, index) {
        return `${category}-${index}`;
    }

    /**
     * ex) 문제 채점 결과 등
     * 현재 문제 순서는 바뀌지 않기 때문에,
     * 하지만 문제 순서를 바꾸게 된다면 그때는 문제 채점 결과를 서버에 저장해야한다.
     */
    function applyDataFromSessionStorage() {
        const categories = document.querySelectorAll('.category');
        categories.forEach(categoryJson => {
            const categoryText = categoryJson.querySelector('.category-text').textContent.trim();
            const questionBoxes = categoryJson.querySelectorAll('.question-box');
            questionBoxes.forEach((questionBox, index) => {
                const storedResult = sessionStorage.getItem(getStorageKey(categoryText, index));
                if (storedResult !== null) {
                    const isCorrect = JSON.parse(storedResult)
                    /*
                    본문 문제 업데이트
                     */
                    markQuestionResult(questionBox, isCorrect)

                    /*
                    사이드바 업데이트
                     */
                    const sideBarQuestion = findSidebarQuestion(categoryText, index);
                    if (sideBarQuestion !== null) {
                        makeSideBarQuestionColor(sideBarQuestion, isCorrect);
                    }

                }
            })
        })
        // 저장된 마지막 선택된 문제로 스크롤
        const savedPosition = sessionStorage.getItem("lastSelectedQuestion");
        if (savedPosition) {
            const {category, index} = JSON.parse(savedPosition);

            // categoryTitle과 questionIndex를 사용해 questionBox를 찾는 함수

            const questionBox = findQuestionInCategory(category, index);
            if (questionBox) {
                questionBox.scrollIntoView({behavior: 'smooth', block: 'start'});
            }
        }
    }

    /*
    사이드 바 생성
     */
    function createQuestionList() {
        const categories = document.querySelectorAll('.category');
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


    // 문제 창에서 카테고리 이름,인덱스를 기준으로 질문 찾기
    function findQuestionInCategory(categoryText, questionIndex) {
        const category = Array.from(document.querySelectorAll('.category'))
            .find(category => category.querySelector('.category-text').textContent.trim() === categoryText);

        return category ? category.querySelectorAll('.question-box')[questionIndex] : null;
    }

// 사이드바에서 카테고리 이름, 인덱스를 기준으로 질문 찾기
    function findSidebarQuestion(categoryText, questionIndex) {
        const sidebarCategory = Array.from(questionList.querySelectorAll('.questionBarCategory'))
            .find(sidebar => sidebar.querySelector('.questionBarCategoryTitleBox').textContent.trim() === categoryText);
        return sidebarCategory ? sidebarCategory.querySelectorAll('a')[questionIndex] : null;
    }

// 질문 클릭 시 해당 질문으로 스크롤
    function handleQuestionListClick(e) {
        if (e.target.tagName === 'A') {
            e.preventDefault();
            const categoryTitle = e.target.closest('.questionBarCategory').querySelector('.questionBarCategoryTitleBox').textContent.trim();
            const questionIndex = e.target.dataset.questionIndex;
            const questionBox = findQuestionInCategory(categoryTitle, questionIndex);
            if (questionBox) questionBox.scrollIntoView({behavior: 'smooth', block: 'start'});
        }
    }

// 사이드바 링크 스타일 업데이트
    function updateSidebar(element, isCorrect) {
        const questionBox = element.closest('.question-box');
        const category = element.closest('.category');
        const questionIndex = Array.from(category.querySelectorAll('.question-box')).indexOf(questionBox);
        const categoryTitle = category.querySelector('.category-text').textContent.trim();


        const sideBarQuestion = findSidebarQuestion(categoryTitle, questionIndex);
        if (sideBarQuestion) {
            makeSideBarQuestionColor(sideBarQuestion, isCorrect);
            sideBarQuestion.scrollIntoView({behavior: 'smooth', block: 'start'});
        }
    }

    function makeSideBarQuestionColor(sideBarQuestion, isCorrect) {
        sideBarQuestion.style.color = isCorrect ? 'green' : 'red';
        sideBarQuestion.style.borderLeft = isCorrect ? '3px solid green' : '3px solid red';
    }


    function handleQuestionClick(e) {
        // 해설 내용을 바꾼다.
        if (e.target.classList.contains('choice-item')) {
            selectChoice(e.target);
            /*
            객관식 문제가 아닐때만 문제 선택을 활성화 한다.
             */
        } else if (multipleChoice === false && e.target.classList.contains('questionItem')) {
            selectQuestion(e.target);
        }
    }

    function selectChoice(choice) {
        if (selectedChoice) {
            selectedChoice.classList.remove('selected-choice');
        }
        selectedChoice = choice;
        selectedChoice.classList.add('selected-choice');

        let questionBox = selectedChoice.closest('.question-box');
        saveQuestionPosition(questionBox);

        refreshAnswerAndDescription();

    }

    /*
    선택된 문제의 위치를 세션에 기억시킨다
     */
    function saveQuestionPosition(questionBox) {
        const category = questionBox.closest('.category').querySelector('.category-text').textContent.trim();
        const index = questionBox.dataset.questionIndex;

        // JSON 형태로 category와 index 저장
        const questionPosition = {
            category: category,
            index: index
        };

        sessionStorage.setItem("lastSelectedQuestion", JSON.stringify(questionPosition));
    }

    function selectQuestion(question) {
        if (selectedQuestion) {
            selectedQuestion.classList.remove('selected-question');
        }
        selectedQuestion = question;
        selectedQuestion.classList.add('selected-question');
        saveOverlayChanges(selectedQuestion);

        refreshAnswerAndDescription();
    }

    function refreshAnswerAndDescription() {
        makeDescriptionForHtml()
        hideAnswerMessage();
        hideDescription();
        marked = false;
    }

    function toggleDescriptionVisibility() {
        if (marked === false) {

        }
        if (descriptionText.style.display === "none") {
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

    // 결과를 기록하는 공통 함수
    function updateQuestionResult(questionBox, isCorrect) {
        markQuestionResult(questionBox, isCorrect)
        //현재 채점 결과를 세션 스토리지에 저장한다.
        const category = questionBox.closest('.category').querySelector('.category-text').textContent.trim();
        const index = questionBox.dataset.questionIndex;
        const storageKey = getStorageKey(category, index);
        sessionStorage.setItem(storageKey, JSON.stringify(isCorrect))
    }


    function markQuestionResult(result, isCorrect) {
        const questionResult = result.querySelector('.questionResult');

        // isCorrect가 null이거나 undefined인 경우 아무것도 표시하지 않음
        if (isCorrect === null || isCorrect === undefined) {
            return;
        }
        questionResult.dataset.result = isCorrect ? "true" : "false";
        markOXResult(questionResult, isCorrect);

    }

    function markOXResult(element, isCorrect) {
        element.textContent = isCorrect ? "O" : "X"; // O 또는 X 표시
    }


// 객관식 문제 처리 함수
    function processMultipleChoice() {
        const questionBox = selectedChoice.closest('.question-box');
        const isCorrect = selectedChoice.getAttribute('data-answer-status') === 'true';
        const selectText = selectedChoice.getAttribute('data-choice-text');

        /**
         * 선택지 정답 표시
         */
        if (isCorrect) {
            selectedChoice.style.color = 'green';
        } else {
            selectedChoice.style.color = 'red';
        }


        if (!questionBox) {
            console.error('해당 문제 상자를 찾을 수 없습니다.');
            return;
        }

        // 공통 로직 호출
        updateQuestionResult(questionBox, isCorrect);

        // 정답/오답 메시지 표시
        showAnswerMessage(isCorrect ? `정답입니다! (${selectText})` : `오답입니다! (${selectText})`, isCorrect);

        // 사이드바 업데이트
        updateSidebar(selectedChoice, isCorrect);
    }

// 주관식 문제 처리 함수
    function processShortAnswer() {
        const questionBox = selectedQuestion.closest('.question-box');

        if (!questionBox) {
            console.error('해당 문제 상자를 찾을 수 없습니다.');
            return;
        }

        // 주관식 문제는 맞은 것으로 처리
        updateQuestionResult(questionBox, true);

        // 주관식 정답 메시지 표시
        showAnswerMessage('주관식 문제는 해설이 바로 표시됩니다.', true);

        // 사이드바 업데이트
        updateSidebar(selectedQuestion, true);
        showDescription();
    }


    function showAnswerMessage(message, isCorrect) {
        answerBox.style.display = 'flex';
        answerBox.classList.toggle('correct-answer', isCorrect);
        answerBox.classList.toggle('wrong-answer', !isCorrect);
        answerText.textContent = message;
    }

    function hideAnswerMessage() {
        answerBox.style.display = 'none';
    }

    function makeDescriptionForHtml() {
        /*
        주관식이냐, 객관식이냐에 출력 내용을 구분한다.
         */
        const description = selectedChoice ? selectedChoice.closest('.each-question').getAttribute('data-description') : selectedQuestion.closest('.each-question').getAttribute('data-description');
        // formatText 함수를 호출하여 포맷팅된 텍스트를 얻음
        descriptionText.innerHTML = formatText(description);
    }


    function showDescription() {
        descriptionText.style.display = "block";
        toggleButton.textContent = "해설 숨기기 ▼";
    }

    function hideDescription() {
        descriptionText.style.display = "none";
        toggleButton.textContent = "해설 보기 ▼";
    }

    function formatText(text) {
        // 줄 바꿈을 <br/>로 변환
        let formattedText = text.replace(/\n/g, '<br/>');

        // #으로 시작하는 텍스트를 대제목과 소제목으로 변환
        formattedText = formattedText.replace(/^### (.*?)(<br\/>|$)/gm, '<span style="font-weight: bold; font-size: 1.2em;">$1</span>');
        formattedText = formattedText.replace(/^## (.*?)(<br\/>|$)/gm, '<span style="font-weight: bold; font-size: 1.4em;">$1</span>');
        formattedText = formattedText.replace(/^# (.*?)(<br\/>|$)/gm, '<span style="font-weight: bold; font-size: 1.6em;">$1</span>');

        // **로 둘러싸인 단어를 굵게 표시
        formattedText = formattedText.replace(/\*\*(.*?)\*\*/g, '<span style="font-weight: bold; font-size: 1em;">$1</span>');

        return formattedText;
    }


});
