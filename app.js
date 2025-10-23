function validateForm () {
    const studentIdE = document.getElementById("student-id")
    const fullNameE = document.getElementById("full-name")
    const pointAvgE = document.getElementById("point-avg")

    const studentId = studentIdE.value.trim()
    const fullName = fullNameE.value.trim()
    const pointAvg = pointAvgE.value

    if (!studentId || !fullName || !pointAvg) {
        alert("Vui lòng nhập đầy đủ thông tin!")
        return false
    }

    return true;
}

function changeTitle() {
    const _DEFAULT_TITLE_TEXT = "Demo JS"
    const _DEFAULT_TITLE_COLOR = "red"

    const titleE = document.getElementById("title")
    titleE.innerHTML = _DEFAULT_TITLE_TEXT
    titleE.style.color = _DEFAULT_TITLE_COLOR
}