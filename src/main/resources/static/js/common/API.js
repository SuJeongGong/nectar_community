/**
 * description    : API 목록
 * fileName       : checkReg
 * author         : GongSuJeong
 * date           : 2022-05-31
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31        GongSuJeong       최초 생성
 */

//check하는 api 호출
function checkAPI(searchType, searchData) {

    fetch('/join/check/' + searchType + '/' + searchData)
        .then((response) => {
            if (!response.ok) {
                console.log("response.status 오류");
                throw new Error("Failed with HTTP code " + response.status);
            }
            return response.json();
        })
        .then((result) => {
            if (!!document.getElementById("resText" + searchType)) {
                document.getElementById("resText" + searchType).remove();
            }
            if (!!document.getElementById("res" + searchType)) {
                document.getElementById("res" + searchType).remove();
            }
            const resultText = document.createElement('div');
            const resultTag = document.createElement('div');
            if (result) {
                resultText.textContent = '사용할 수 있는 아이디 입니다.';
                resultText.classList.add("text-success");
                resultTag.textContent = '1';
            } else {
                resultText.textContent = '사용할 수 없는 아이디 입니다.';
                resultText.classList.add("text-danger");
                resultTag.textContent = '0';
            }
            resultTag.id = "res" + searchType;
            resultTag.style = "display:none;";
            resultText.id = "resText" + searchType;
            document.getElementById(searchType).parentElement.appendChild(resultTag);
            document.getElementById(searchType).parentElement.appendChild(resultText);
            return result;
        })
        .catch((error) => {
            console.log(error);
        });
}


//이메일 전송
function checkAPI(url) {

    fetch(url, {method:"post"})
        .then((response) => {
            if (!response.ok) {
                console.log("response.status 오류");
                throw new Error("Failed with HTTP code " + response.status);
            }
            return response.json();
        })
        .then((result) => {
            alert("재전송을 완료했습니다.");
        })
        .catch((error) => {
            console.log(error);
        });
}