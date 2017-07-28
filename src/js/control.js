// $().ready(function () {
//     $("input[type='Text']").addEventListener("focus", function (it) {
//             var length = it.value.length;
//             if (it.createTextRange()) {
//                 var range = it.createTextRange();
//                 range.move('character', length);
//                 range.select();
//             } else {
//                 if (it.selectionStart) {
//                     it.focus();
//                     it.setSelectionRange(caretPos, caretPos);
//                 }
//                 else {
//                     it.focus();
//                 }
//             }
//         }
//     );
// })