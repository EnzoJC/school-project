const prevBtns = document.querySelectorAll("#prevBtn");
const nextBtns = document.querySelectorAll("#nextBtn");
const items = document.querySelectorAll(".item");
const formSteps = document.querySelectorAll(".step-form");

let formStepsNum = 0;

nextBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
        formStepsNum++;
        updateFormSteps();
        updateProgressbarNext();
    });
});

prevBtns.forEach((btn) => {
    btn.addEventListener("click", () => {
        formStepsNum--;
        updateFormSteps();
        updateProgressbarBack();
    });
});

function updateFormSteps() {
    formSteps.forEach((formStep) => {
        formStep.classList.remove("step-form-active");
    });
    formSteps[formStepsNum].classList.add("step-form-active");
}
function updateProgressbarBack() {
    items[formStepsNum+1].classList.remove("active")
}
function updateProgressbarNext() {
    items[formStepsNum].classList.add("active")
}