// 페이지 로드 후 실행
document.addEventListener("DOMContentLoaded", function() {
    const descriptions = document.querySelectorAll('.description');
    console.log("Descriptions found:", descriptions.length);  // 몇 개의 description이 있는지 확인

    descriptions.forEach(function(element) {
        console.log("Before replace: ", element.textContent);  // 각 description의 텍스트 출력
        element.innerHTML = element.textContent.replace(/\n/g, '<br/>');
        console.log("After replace: ", element.innerHTML);
    });
});