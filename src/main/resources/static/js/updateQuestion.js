function toggleApprove(questionId) {
    fetch(`/admin/question/normal/toggle-approve/${questionId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to toggle approve');
            }
            return response.json();
        })
        .then(data => {
            console.log('Approve status toggled successfully:', data);
        })
        .catch(error => {
            console.error('Error toggling approve status:', error);
        });
}

// canBeMultiple 상태를 토글하는 함수
function toggleCanBeMultiple(questionId) {
    fetch(`/admin/question/normal/toggle-multiple/${questionId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to toggle canBeMultiple');
            }
            return response.json();
        })
        .then(data => {
            console.log('Can be multiple status toggled successfully:', data);
        })
        .catch(error => {
            console.error('Error toggling canBeMultiple status:', error);
        });
}
// 카테고리별 문제 목록 토글 함수 (카테고리 이름 기반)
function toggleCategory(categoryName) {
    var questionList = document.getElementById('questions-' + categoryName);
    if (questionList.style.display === "none") {
        questionList.style.display = "block";
    } else {
        questionList.style.display = "none";
    }
}
function deleteQuestion(questionId) {
    if (confirm("Are you sure you want to delete this question?")) {
        fetch(`/admin/question/normal/${questionId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to delete question');
                }
                // 성공 시 삭제된 질문을 화면에서 숨김
                document.getElementById('question-' + questionId).style.display = 'none';
            })
            .catch(error => {
                console.error('Error deleting question:', error);
            });
    }
}
