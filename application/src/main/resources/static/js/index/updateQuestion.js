function toggleApprove(questionId) {
    fetch(`/admin/question/common/${questionId}/toggle-approve`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('승인 상태 토글 실패');
            }
            return response.json();
        })
        .then(data => {
            console.log('승인 상태가 성공적으로 토글되었습니다:', data);
        })
        .catch(error => {
            alert('승인 상태 토글 중 오류 발생:' + error);

        })
        .finally(() => {
            window.location.reload(); // 함수 호출
        });
}

function toggleCanBeMultiple(questionId) {
    fetch(`/admin/question/common/${questionId}/toggle-multiple`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('중복 선택 가능 상태 토글 실패');
            }
            return response.json();
        })
        .then(data => {
            console.log('중복 선택 가능 상태가 성공적으로 토글되었습니다:', data);
        })
        .catch(error => {
            alert('중복 선택 가능 상태 토글 중 오류 발생:' + error);
        })
        .finally(() => {
            window.location.reload(); // 함수 호출
        });
}

function toggleCategory(categoryName) {
    var questionList = document.getElementById('questions-' + categoryName);
    if (questionList.style.display === "none") {
        questionList.style.display = "block";
    } else {
        questionList.style.display = "none";
    }
}

function deleteQuestion(questionId) {
    if (confirm("정말로 이 질문을 삭제하시겠습니까?")) {
        fetch(`/admin/question/common/${questionId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('질문 삭제 실패');
                }
                document.getElementById('question-' + questionId).style.display = 'none';
            })
            .catch(error => {
                alert('질문 삭제 중 오류 발생:' + error);
            })
            .finally(() => {
                window.location.reload(); // 함수 호출
            });
    }
}

function updateDifficulty(selectElement) {
    const questionId = selectElement.getAttribute('data-question-id');
    const newDifficulty = selectElement.value;

    fetch(`/admin/question/common/${questionId}/difficulty`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({difficulty: newDifficulty})
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('난이도 업데이트 실패');
            }
            return response.json();
        })
        .then(data => {
            console.log('난이도가 성공적으로 업데이트되었습니다:', data);
        })
        .catch(error => {
            alert('난이도 업데이트 중 오류 발생:' + error);
        });
}

function makeEditable(element, field, questionId) {
    const originalText = element.innerText;
    const input = document.createElement('input');
    input.type = 'text';
    input.value = originalText;
    input.style.width = '100%';

    input.onkeydown = function (event) {
        if (event.key === 'Enter') {
            const newText = input.value;
            updateNormalTextField(questionId, field, newText);
            element.innerText = newText;
            element.ondblclick = () => makeEditable(element, field, questionId);
        }
    };

    element.innerHTML = '';
    element.appendChild(input);
    input.focus();
    element.ondblclick = null;
}

let currentEditElement = null;

// 페이지 로드 시 실행될 함수
document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.editable-container').forEach(container => {
        container.addEventListener('dblclick', function (e) {
            const editableElement = container.firstElementChild;
            showOverlayInput(editableElement);
        });
    });
});
document.addEventListener('DOMContentLoaded', function () {
    // 저장된 스크롤 위치가 있다면 해당 위치로 스크롤
    const savedScrollPosition = localStorage.getItem('scrollPosition');
    if (savedScrollPosition) {
        window.scrollTo(0, parseInt(savedScrollPosition));
        localStorage.removeItem('scrollPosition'); // 사용 후 제거
    }
});

/*
문제, 해설 덮어씌우기
 */

function showOverlayInput(element) {
    currentEditElement = element;
    const overlay = document.getElementById('overlay-input');
    const textarea = document.getElementById('overlay-textarea');

    const rect = element.getBoundingClientRect();
    overlay.style.top = `${rect.top + window.scrollY}px`;
    overlay.style.left = `${rect.left + window.scrollX}px`;
    overlay.style.width = `${rect.width}px`;
    overlay.style.height = `${rect.height}px`;

    // <br> 태그를 줄 바꿈 문자로 변환
    textarea.value = element.innerHTML.replace(/<br\s*\/?>/g, '\n');
    textarea.style.fontSize = window.getComputedStyle(element).fontSize;
    textarea.style.fontWeight = window.getComputedStyle(element).fontWeight;
    textarea.style.color = window.getComputedStyle(element).color;

    overlay.style.display = 'flex';
    textarea.focus();
}

function saveOverlayChanges() {
    if (currentEditElement) {
        const newText = document.getElementById('overlay-textarea').value;
        const questionId = currentEditElement.getAttribute('data-id');
        const field = currentEditElement.getAttribute('data-field');

        updateNormalTextField(questionId, field, newText)
            .then(response => {
                if (response.ok) {
                    console.log(`성공적으로 업데이트되었습니다:`);

                    // 현재 스크롤 위치 저장
                    localStorage.setItem('scrollPosition', window.pageYOffset);

                    // 성공 시 페이지 새로고침
                    window.location.reload();
                } else {
                    console.warn(`서버 응답에 ${field} 필드가 없거나 응답이 올바르지 않습니다.`);
                    alert('업데이트에 문제가 발생했습니다. 페이지를 새로고침한 후 다시 시도해주세요.');
                }
            })
            .catch(error => {
                console.error(`${field} 업데이트 실패:`, error);
                alert('변경사항을 저장하는 데 실패했습니다. 페이지를 새로고침한 후 다시 시도해주세요.');
            })
            .finally(() => {
                document.getElementById('overlay-input').style.display = 'none';
                currentEditElement = null;
            });
    } else {
        document.getElementById('overlay-input').style.display = 'none';
    }
}

function updateNormalTextField(questionId, field, newValue) {
    // 경로를 동적으로 설정
    const apiUrl = field === 'gradeStandard'
        ? `/admin/questions/major/descriptive/${questionId}/standard`
        : `/admin/question/common/${questionId}/${field}`;

    return fetch(apiUrl, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({[field]: newValue})
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`${field} 업데이트 실패`);
            }
            return response;
        })
}


// ... (나머지 코드는 그대로 유지) ...

function cancelOverlayChanges() {
    document.getElementById('overlay-input').style.display = 'none';
    currentEditElement = null;
}

// 이미지 업로드 함수
function uploadImage(questionId, fileInput) {
    const formData = new FormData();
    formData.append("image", fileInput.files[0]);
    console.log(formData)

    fetch(`/admin/question/common/${questionId}`, {
        method: 'PATCH',
        body: formData
    }).then(response => {
        if (!response.ok) {
            console.warn("서버 응답이 성공 상태가 아님:", response.status);
        }
        return response.text();  // JSON이 아닌 응답도 텍스트로 받음
    }).then(data => {
        console.log("이미지 업로드 성공:", data);  // 여기서 URL을 로그에 표시합니다
        location.reload(); // 조건 없이 새로고침하여 업로드된 이미지 표시
    }).catch(error => alert("이미지 업로드 실패:" + error))
        .finally(() => {
            window.location.reload(); // 함수 호출
        });
}

// 이미지 제거 함수
function removeImage(questionId) {
    // 제거 로직을 추가하거나 API를 확장하여 이미지 제거 기능 구현
    console.log("이미지 제거:", questionId);
}
