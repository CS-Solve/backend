document.addEventListener("DOMContentLoaded", function() {
    const descriptions = document.querySelectorAll('.description');
    descriptions.forEach(function(element) {
        element.innerHTML = element.textContent.replace(/\n/g, '<br/>');
    });
});