const plateList = document.getElementById("plate_list");
const leftPlates = document.getElementById("left_plate");
const rightPlates = document.getElementById("right_plate");
const btn = document.getElementById("btn");
const input = document.getElementById("input");
const warning = document.getElementById("warning");
const msg = document.getElementById("msg");

let left = 0, right = 0;
btn.addEventListener("click", btnClick);

//checks button submission
function btnClick() {
    if (plateWeightLimit(parseFloat(input.value))) {
        warning.hidden = true;
        addPlateToTheBottomList(input.value);
    } else
        warning.hidden = false;
}

//checks bar mass
function plateWeightLimit(weight) {
    return weight >= 0 && weight <= 20 && weight !== "";
}

//adding plate to the list
function addPlateToTheBottomList(weight) {
    let node = document.createElement('div');
    node.innerHTML = weight;
    node.classList.add('plate');
    node.id = 'plate' + Date.now();
    plateList.appendChild(node);
}

//adding plates to the left
plateList.addEventListener("click", function (event) {
    let target = event.target;
    if (target.parentElement.className !== 'plate_list') return;

    left += parseInt(target.innerHTML);
    addToBar(target, "left", leftPlates);
    plateList.children[target.id].remove();
    ifReady();
});

//adding plates to the right
plateList.addEventListener("contextmenu", function (event) {
    let target = event.target;
    if (target.parentElement.className !== 'plate_list') return;

    right += parseInt(target.innerHTML);
    addToBar(target, "right", rightPlates);
    plateList.children[target.id].remove();
    ifReady();
});

//adding plates to the bar
function addToBar(target, location, locSide) {
    let node = document.createElement('div');
    node.innerHTML = target.innerHTML;
    node.classList.add('plate');
    node.id = location + 'plate' + Date.now();
    locSide.appendChild(node);
}

//removing plates from the left
leftPlates.addEventListener("click", function (event) {
    let target = event.target;
    if (target.parentElement.className !== 'bar_plates') return;

    left -= parseInt(target.innerHTML);
    addPlateToTheBottomList(target.innerHTML);
    leftPlates.children[target.id].remove();
    ifReady();
});

//removing plates from the right
rightPlates.addEventListener("click", function (event) {
    let target = event.target;
    if (target.parentElement.className !== 'bar_plates') return;

    right -= parseInt(target.innerHTML);
    addPlateToTheBottomList(target.innerHTML);
    rightPlates.children[target.id].remove();
    ifReady();
});

//checks if barbell is ready
function ifReady() {
    if (left === right && left !== 0)
        msg.innerHTML = "Barbell is ready!";
    else
        msg.innerHTML = "...";
}

$(document).ready(() => {

    $('.product-photo').on('mouseenter', event => {
        $(event.currentTarget).addClass('photo-active')
    }).on('mouseleave', event => {
        $(event.currentTarget).removeClass('photo-active')
    })
});
