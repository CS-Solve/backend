function loadComments() {
    // sessionStorage에서 저장된 데이터 가져오기
    const savedQuestionData = JSON.parse(sessionStorage.getItem("lastSelectedQuestion"));
    const questionId = savedQuestionData ? savedQuestionData.questionId : null;

    if (!questionId) {
        alert("선택된 질문이 없습니다.");
        return;
    }

    // AJAX 요청
    $.ajax({
        url: `/question/${questionId}/comment`, // questionId를 URL에 포함
        method: 'GET',
        success: function (data) {
            // 이미 모달이 있다면 제거
            $('#commentModal').remove();
            // 새 모달 HTML을 추가하고 표시
            $('body').append(data);
            $('#commentModal').modal('show');

            // 모달이 로드된 후에 questionItem 요소들을 포맷팅
            $('#commentModal .specific-question').each(function () {
                formatSpecificQuestion(this);
            });
        },
        error: function () {
            alert('댓글을 불러오는 데 실패했습니다.');
        }
    });
}

function formatSpecificQuestion(element) {
    // 본문 내용은  기본 글자를 크게 한다
    element.style.fontWeight = 'bold';
    element.style.fontSize = "1.5vh";

    // element의 텍스트를 줄바꿈으로 나누어 배열로 저장
    let lines = element.textContent.split('\n');

    // 각 줄을 처리하여 포맷팅
    let formattedLines = lines.map(line => {
        let formattedLine = line;

        // 줄의 시작 부분에서 #을 확인하여 대제목과 소제목으로 변환
        if (formattedLine.startsWith('# ')) {
            formattedLine = formattedLine.replace(/^# (.*)/, '<span style="font-weight: bold; font-size: 1.6em;">$1</span>');
        } else if (formattedLine.startsWith('## ')) {
            formattedLine = formattedLine.replace(/^## (.*)/, '<span style="font-weight: bold; font-size: 1.4em;">$1</span>');
        } else if (formattedLine.startsWith('### ')) {
            formattedLine = formattedLine.replace(/^### (.*)/, '<span style="font-weight: bold; font-size: 1.2em;">$1</span>');
        }

        // **로 둘러싸인 단어를 굵게 표시
        formattedLine = formattedLine.replace(/\*\*(.*?)\*\*/g, '<span style="font-weight: bold; font-size: 1em;">$1</span>');

        return formattedLine; // 포맷팅된 줄 반환
    });

    // 포맷팅된 줄들을 다시 줄바꿈으로 연결
    element.innerHTML = formattedLines.join('<br/>');
}

/**
 * 댓글 작성
 */
function submitComment() {
    const questionId = document.getElementById('commentModal').getAttribute('data-question-id');
    const commentContent = document.getElementById('newComment').value;
    if (!commentContent) {
        alert("댓글을 입력하세요.");
        return;
    }
    $.ajax({
        url: `/question/${questionId}/comment`,
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({content: commentContent}),
        success: function () {
            alert("댓글이 등록되었습니다.");
            reloadComments(questionId); // 댓글 목록 갱신
        },
        error: function (jqXHR) {
            if (jqXHR.status === 401) {
                alert("로그인이 필요합니다.");
            } else {
                alert("댓글 등록에 실패했습니다.");
            }
        }
    });
}

// 댓글 삭제
function deleteComment(commentId) {
    const questionId = document.getElementById('commentModal').getAttribute('data-question-id');

    if (!confirm("댓글을 삭제하시겠습니까?")) {
        return;
    }

    fetch(`/question/comment/${commentId}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            alert("댓글이 삭제되었습니다.");
            reloadComments(questionId); // 댓글 목록 갱신
        } else {
            alert("댓글 삭제에 실패했습니다.");
        }
    }).catch(error => {
        console.error('Error:', error);
        alert("오류가 발생했습니다.");
    });
}

/**
 * 댓글 초기화
 */
function reloadComments(questionId) {
    $.ajax({
        url: `/question/${questionId}/comment`, // 최신 댓글 목록을 가져오기 위한 URL
        method: 'GET',
        success: function (data) {
            // 댓글 목록을 업데이트할 영역을 찾아서 새로 불러온 HTML로 교체
            $('#commentModal .modal-body.comment').html($(data).find('.modal-body.comment').html());
        },
        error: function () {
            alert('댓글을 갱신하는 데 실패했습니다.');
        }
    });
}

