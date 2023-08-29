# quiz-test-app
Object oriented programming project
Mục lục:
1. Giới thiệu đề tài
2. Chi tiết đề tài
3. Q&A

1) Giới thiệu đề tài
TÊN ĐỀ TÀI:
PHẦN MỀM TẠO NG N HÀNG ĐỀ VÀ TỔ CHỨC THI TRẮC NGHIỆM
Phiên bản của đề tài:
0612
THỜI GIAN LÀM:
12-16 TUẦN
SỐ NGƯỜI LÀM VIỆC:
TỐI ĐA 5
MÔN HỌC:
 IT3100
ỨNG DỤNG:
Các SV có thể dùng nó để tự tạo đề thi trắc nghiệm và tự ôn tập

Barem:
Bài 1: 1 điểm
Bài 2: 2 điểm
Bài 3: 6 điểm
Bài 4: 2 điểm
Bài 5: 1 điểm
Bài 6: 2 điểm
Bài 7: 4 điểm
Bài 8: 2 điểm
Tổng cộng: 20 điểm

2) Chi tiết đề tài
======================================================================
Bài 1 (1 điểm): Viết chương trình có các giao diện sau đây
GUI (1.1): Giao diện màn hình danh sách thi

GUI (1.2): Giao diện Pop up cài đặt câu hỏi


Chương trình phải thực hiện được việc hiển thị Popup 1.2 trong giao diện 1.1: nhấn vào nút cài đặt (góc cao bên tay phải)







Bài 2 (2 điểm): Viết chương trình có các giao diện sau đây
GUI (2.1): Giao diện Xem danh sách câu hỏi

GUI (2.2) Giao diện xem danh sách category:


Từ giao diện (1.2), chuyển sang được (2.1) bằng cách nhấn chọn Question. Từ giao diện (2.1) nhấn vào Default sẽ hiện ra một danh sách dạng cây. Con số đằng sau tên category là số câu hỏi. Nếu không có câu hỏi nào thì sẽ không có gì đi đằng sau tên














Bài 3 (6 điểm): Viết chương trình có các giao diện sau đây
GUI (3.1): Giao diện Liệt kê câu hỏi (1 điểm)

GUI (3.2) Giao diện tạo mới câu hỏi: (1 điểm)




Vẫn có thể chèn bức ảnh vào trong giao diện soạn câu hỏi được. (1 điểm)

Từ giao diện (3.1), chuyển sang được (3.2) bằng cách nhấn Create new question. Danh sách các mức điểm của câu này đi từ 100% cho đến -83.33% (sau 10% sẽ là 5% và -5%). Danh sách này chỉ hiện ra nếu nhấn vào Grade, mặc định của Grade là None.
Ở giao diện (3.2): nhấn vào Blanks for 3 more choices sẽ hiện ra bổ sung thêm 3 Choice nữa. Trong các choice vẫn có thể chèn các bức ảnh được (1 điểm)
Nhấn vào Save changes and continue editing sẽ vẫn ở màn hình hiện tại. Nhấn vào Save changes (hoặc Cancel) sẽ chuyển sang giao diện 3.1
Các ô có dấu chấm than đỏ sẽ là các ô bắt buộc nhập nội dung
Ở giao diện 3.1, nếu chọn Edit sẽ chuyển sang giao diện giống 3.2 nhưng với các nội dung đã được chèn đủ trong các ô văn bản (và cả Grade). Ngoài ra chữ màu đỏ Adding a Multiple… sẽ trở thành Editing Multiple …

Ở giao diện (3.1), nhấn vào Category sẽ sang GUI (3.3) 
GUI (3.3) Giao diện thêm category (1 điểm)






Ở giao diện (3.1), nhấn vào Import sẽ sang GUI (3.4)
GUI (3.4) Giao diện Import (0.5 điểm)


Khi import thành công sẽ hiện ra một MessageBox (hay có thể gọi là JOptionPanel) với chữ OK. Nếu thất bại (do file sai định dạng Aiken) thì hiển thị dòng đầu tiên bị lỗi hoặc Wrong Format nếu người dùng cố ý chọn file không phải là file văn bản (như EXE, DLL, PDF…) (0.5 điểm)



Bài 4 (2 điểm): kiểm tra định dạng Aiken format trong file
Aiken format là định dạng các câu hỏi trắc nghiệm với quy tắc như sau
Dòng đầu tiên là tiêu đề câu hỏi. Nếu câu hỏi được lưu dưới dạng file thì tất cả tiêu đề của một câu hỏi đều nằm trên cùng một dòng
Tối thiểu hai dòng sau đó là danh sách các phát biểu (tức câu trắc nghiệm phải có tối thiểu hai đáp án A, B). Bắt đầu là ký tự viết hoa, sau đó là một dấu chấm và một khoảng trắng. Sau khoảng trắng đó có tối thiểu một ký tự khác space
Dòng cuối cùng là dòng ANSWER, mô tả đáp án đúng. Sau từ ANSWER là một dấu hai chấm và một khoảng trắng, tiếp đến là một ký tự viết hoa sau cho ký tự này nằm trong danh sách các đáp án ở trên. Sau dòng ANSWER phải có một dòng trống
Như dưới đây là hai câu hỏi theo đúng định dạng Aiken Format
Một cộng một theo em bằng mấy?
A. 2
B. 3
ANSWER: A

Easy: Trong tiếng Việt, chủ nhật còn có tên gọi là gì?
A. Chúa nhật
B. Sunday
C. Ngày tất niên
ANSWER: A

a) Viết chức năng đọc đầu vào là file văn bản đuôi .txt. Trong file này không được có hình ảnh. Nếu một dòng nào đó trong file không tuân thủ đúng định dạng thì in ra “Error at” + số dòng của file đó. Nếu tất cả các dòng đều tuân thủ đúng định dạng thì in ra “Success ” + số câu hỏi của file đó. (1 điểm)
b) Bổ sung câu trên với file đầu vào là file DOCX. Trong file được phép có hình ảnh (1 điểm)

Bài 5 (1 điểm): Xây dựng chương trình với giao diện (5.1) sau

Giao diện này được chuyển từ (1.1) khi người dùng nhấn chọn Turn editing on. 
Tuỳ chọn Open attempts are submitted automatically là mặc định (không đổi được). 
Ở dưới giao diện này sẽ có một nút Create và Cancel. Nút Create khi nhấn xong sẽ bổ sung thêm một Quiz với tên xuất hiện trong giao diện (1.1)

Bài 6 (2 điểm): Xây dựng chương trình với giao diện (6.1) sau

Giao diện này có được nhờ việc nhấn vào “Thi giữa kỳ 2 môn Công nghệ” ở giao diện (1.1)

Nếu nhấn vào nút Cài đặt ở góc trên bên tay phải sẽ chuyển sang giao diện (6.2a) sau đây


Nút tích Shuffle nghĩa là các câu hỏi trắc nghiệm sẽ bị tráo đổi đáp án.
Nút Repaginate bỏ qua không làm
Nút Select Multiple item nghĩa là chọn nhiều câu trắc nghiệm (cho thao tác delete)
Nút Add khi được nhấn sẽ hiện ra như sau - giao diện (6.2b)

a) Thêm toàn bộ câu hỏi trong question bank (1 điểm)
Khi chọn tiếp mục “From question bank” sẽ ra giao diện (6.3a) như sau. Chú ý category sẽ phải được chọn chứ chương trình không tự chọn category. 


Bên dưới giao diện này sẽ có một nút như sau - giao diện (6.3b)


Khi nhấn vào nút Add Selected Question to the Quiz sẽ thấy hiện ra như sau (giao diện 6.4)

Do có 67 câu được thêm vào nên tổng điểm là 67 điểm
b) Thêm một số câu hỏi trong question bank (1 điểm)
Trường hợp chọn tuỳ chọn Add “a random question” ở giao diện  sẽ thấy giao diện (6.5) như sau


Người dùng sẽ có thể chọn ra số lượng câu hỏi muốn thêm vào, các câu này lấy từ ngân hàng đề 67 câu của Sinh học kỳ 2 L7
Chú ý nếu tích vào “Includes questions from subcategories too” sẽ thêm vào trong danh sách (bên dưới) các câu hỏi của các categories con.

Bài 7 (4 điểm)
Sau khi đã thêm các câu hỏi, người dùng có thể quay lại màn hình chuẩn bị thi - giao diện 7.1 (1 điểm)
Nhấn vào Preview Quiz Now sẽ hiện ra cửa sổ yêu cầu xác nhận - giao diện 7.2 (1 điểm)

Giao diện của màn hình làm bài thi - giao diện 7.3 (sản phẩm BTL hãy bỏ không hiện Edit question) (1 điểm)
Trong màn hình 8.3 này, người dùng có thể nhìn thấy tất cả các câu trong đề, tích chọn đáp án cũng như chọn lựa câu hỏi (góc màn hình bên tay phải) 

Giao diện cũng có chưa ô đồng hồ thời gian giảm dần 
Khi người dùng nhấn vào ô Finish attempt…, chương trình sẽ hỏi lại để xác nhận. Nếu sau đó người dùng xác nhận là muốn nộp bài thì hệ thống sẽ chấm điểm và hiển thị lại bài làm cùng với điểm số và các câu đúng của câu hỏi.
Giao diện 7.4 là một ví dụ của phần kết quả bài thi. Chú ý không hiện nút “Edit question” (1 điểm)

Khi nhấn vào Finish review ở giao diện 7.4 sẽ chuyển về giao diện 1.1

Bài 8 (2 điểm): Ở giao diện (7.2), bổ sung thêm một nút Export. Sao cho nếu người dùng nhấn vào Export sẽ xuất ra file PDF với nội dung giống với những câu hỏi trong đề thi. Thứ tự có thể tuỳ ý. Nếu file PDF xuất ra không có hình ảnh (mà ngân hàng đề có hình ảnh) thì sẽ chỉ được 1 điểm
====================================================
Các chức năng nâng cao (sẽ được cộng thêm điểm)
Bài 9 (1 điểm): nâng cấp bài 8 sao cho trước khi tạo ra file thì cho phép người dùng thiết lập được mật khẩu mở file 
Bài 10 (1 điểm): nâng cấp bài 3 sao cho người dùng có thể chèn ảnh động (GIF) vào câu hỏi
Bài 11 (1 điểm): nâng cấp bài 3 sao cho người dùng có thể chèn video ngắn (trên 1s và dưới 10s) vào câu hỏi


3) Q&A
=====================================================
Q&A:
Có một số bạn hỏi về bài tập lớn nên thầy note ra đây các câu trả lời:
1) Làm C# sẽ được thầy cho cao điểm hơn: không phải. Theo ý của thầy nếu bạn nào quyết tâm làm chức năng đọc file DOCX đầu vào thì nên dùng C#. Còn không thì tuỳ, miễn là một trong hai ngôn ngữ Java hoặc C# đều được
2) Có được dùng các framework hỗ trợ không (như Spring, ASP.NET): được, các bạn được phép dùng bất kỳ framework nào hỗ trợ cho hai ngôn ngữ Java và C#
3) Có được dùng chatGPT không? Được
4) Có được dùng database không? Được. Nhưng nó không phải là input đầu vào hoặc output đầu ra. Nó chỉ đóng vai trò hỗ trợ cho phần mềm hoạt động thôi.
5) Nhóm được phép có trên 5 người không? Hiện tại thầy vẫn cho rằng nhóm chỉ nên có 5 người trở xuống
6) Yêu cầu BTL đã chốt chưa? Thẳng thắn mà nói thì khó có yêu cầu phần mềm nào chốt ngay từ giai đoạn đầu, nhưng nếu giờ BTL có cập nhật thì sự chỉnh sửa đó hầu hết chỉ là viết lại câu chữ cho rõ ràng hơn.
7) Phiên bản 0323 khác gì với phiên bản trước?
Đã có bổ sung thêm mục Q&A và mục số hiệu phiên bản. Bài 4 (cũ) giờ biến thành bài 8. Các bài cũ từ 5 đến 8 trở thành các bài từ 4 đến 7. 
8) Báo cáo BTL sẽ được viết như nào?
Không cần viết báo cáo. Chỉ cần tất cả thành viên trong nhóm, mỗi người điền vào form online để trả lời các câu hỏi như sau: (i) Họ tên; (ii) MSSV; (iii) MSSV nhóm trưởng; (iv) Phần trăm đóng góp của em vào BTL (sao cho tổng tất cả thành viên là 100%); (v) link github/gitlab của mã nguồn; (vi) số lượng thành viên ban đầu khi đăng ký của nhóm; (vii) Câu hỏi đặc biệt khác (mà thầy giao riêng cho em phải trả lời sau khi bảo vệ)
9) Nếu em không thể xây dựng xong giao diện GUI thì em làm giao diện Console có được không?
Được. Khi ấy điểm của em sẽ thấp hơn so với điểm 20. Cụ thể: Bài 1: 0 điểm; bài 2: 0.5 điểm (nếu tạo được các category cha/con); bài 3: 1 điểm (nếu hoàn thành chức năng thêm từng câu hỏi mới bằng tay) - 0.5 điểm (nếu chỉnh được mức % cho từng đáp án) -  0.5 điểm (nếu import được file); bài 4: 1 điểm (kiểm tra định dạng file txt) - 1 điểm (kiểm tra định dạng file DOCX); bài 5: 0 điểm; bài 6: 0.5 điểm (nếu tạo N câu hỏi từ ngân hàng đề N câu) - 0.5 điểm (nếu tạo N câu hỏi từ ngân hàng đề M câu, M > N); bài 7: 0.5 điểm (đọc file bài làm) - 1 điểm (chấm điểm bài làm); bài 8: 1 điểm (nếu xuất ra PDF không có hình ảnh) - 1 điểm (nếu xuất PDF có hình ảnh); bài 9: 1 điểm (nếu gắn được mật khẩu cho file). Vậy nếu không làm GUI thì điểm của SV có thể được 10/20 điểm.
10) trong bài tập lớn lập trình hướng đối tượng thì mọi câu hỏi đều được mặc định số điểm là 1.00 và không sửa được đúng không?
Đúng vậy. Để đơn giản thì mọi câu hỏi đều không cho sửa điểm. Nhưng nội dung câu hỏi và các đáp án (cũng như đáp án đúng) đều sửa được.
11) một số chức năng có hiện ở ảnh thầy để nhưng thầy không đề cập thì có cần làm không, chẳng hạn như:


Trả lời: Chức năng “Also show questions from subcategories" thì cần làm. Chức năng Edit thì cần làm (nhưng dấu xổ xuống thì không cần làm). Chức năng tích chọn (ở vòng eclipse đen bên trái) thì không cần làm
12) Giao diện tạo câu hỏi mới thì một câu hỏi có cần bắt buộc có ít nhất 2 lựa chọn trở lên không?
Trả lời: có. Cần bắt buộc có ít nhất 2 lựa chọn trở lên.
13) Ngoài ra thì các lựa chọn có cần điền đúng thứ tự không?
Trả lời: không cần điền đúng thứ tự. Lựa chọn 1 và 5 điền, các lựa chọn khác bỏ trống cũng được, khi ấy hệ thống cần gom các lựa chọn lại (bỏ qua các lựa chọn trống 2, 3, 4)
14) Với phần thêm ảnh thì thêm được vào những phần nào? hỉ cần ở question name thôi hay cả question text với mấy choices nữa?
Trả lời: thêm ảnh thì thêm được cả vào question text và choices. Chứ không thể thêm ảnh vào phần question name
15) "Nếu câu hỏi được lưu dưới dạng file thì tất cả tiêu đề của một câu hỏi đều nằm trên cùng một dòng", ví dụ của câu hỏi này?
Trả lời: chẳng hạn như câu hỏi sau là một câu hỏi mà tiêu đề nằm trên các dòng khác nhau
C1D222: Theo em chương trình sau in ra gì?
#include <stdio.h>
int main(){
	printf(“Hello World");
	return 0;
}
A. In ra “Hello"
B. In ra “World"
C. In ra “HW"
D. In ra “Hello World"
E. In ra “World Hello"
ANSWER: D
Ta có thể thấy tiêu đề câu hỏi bắt đầu từ “Theo em" cho đến “}”, nếu câu hỏi này ở trong file thì nên nằm trên 1 dòng như sau
C1D222: Theo em chương trình sau in ra gì? #include <stdio.h> int main(){ printf(“Hello World");	return 0; }
16) Chức năng của "Also show questions from subcategories" trong Gui2.1 là gì?
Cái tích chọn đó là để lấy được các câu hỏi trong subcategory. Chẳng hạn ta tạo ra một category mang tên "Chương 1", và ta có 10 câu hỏi trong đó, nhưng sau đấy ta tạo ra category con bên trong "Chương 1" (mang tên "Chương 1 nâng cao" chẳng hạn) và ta để vào trong đó 5 câu. Như thế khi ta tích chọn "Also show questions from SUBcategories" thì số lượng câu hỏi được chọn (để được ngẫu nhiên xuất hiện trong đề thi) sẽ là 10 + 5 = 15. Ngược lại nếu không chọn thì số lượng câu hỏi (để được ngẫu nhiên xuất hiện trong đề thi) chỉ là 10.

17) Chức năng của "Also show old questions" trong Gui2.1 là gì?
Bỏ qua không làm chức năng này
18) Dòng chữ "Questions matching this filte ***"( đã bị che mất bởi treeView) là gì và chức năng của nó là gì?.Chức năng của dãy danh sách ở dưới label này là gì?

Dòng chữ đó đầy đủ là "Questions matching this filter". Dòng chữ đó thể hiện bản danh sách đó là danh sách các câu hỏi được chọn ở bước trước. Chẳng hạn ta chọn category “Chương 1” (với 10 câu) thì bên dưới sẽ hiện ra đủ 10 câu đó (nếu dài hơn sẽ có phân trang). Còn nếu ta tích chọn thêm “Chương 1 nâng cao” (giả sử đó là subcategory của “Chương 1” với 5 câu) thì bên dưới sẽ hiện đủ 15 câu
Còn cái list hiện xuống là biểu thị số câu hỏi sẽ xuất hiện trong đề thi. Số đó đương nhiên bé hơn hoặc bằng số câu hỏi được chọn.
19) Trong GUI 3.2, khi ấn nút "Save changes and continue editting" thì phải điền hết thông tin câu hỏi mới được lưu, hay điền một phần thôi cũng lưu được?
Điền một phần thôi cũng lưu được
20) Trong GUI 3.1, khi ấn các nút "Edit" trong cột Action, có được sửa category không? hay chỉ sửa nội dung câu hỏi?
Sửa được cả category nữa 
21) Trong GUI 5.1, checkbox "Enable" có ý nghĩa gì?
Ô tích Enable đó có nghĩa là nếu người dùng muốn thiết lập thời gian (ví dụ thời gian bắt đầu thi) thì nhấn vào ô tích Enable cho phép người dùng sửa được thời gian đó. Như chúng ta thấy ở GUI 5.1, Enable không được tích khiến các danh sách chọn giờ/ngày/tháng.. có màu xám (và sẽ không có biến đổi gì khi bị nhấn vào vùng xám đó). Nếu enable được tích thì mới thiết lập được giờ/ngày/tháng/... (xem ảnh đính kèm). Tuy vậy SV không cần làm cái enable cho “Open the quiz" và “Close the quiz", chỉ cần làm cho “Time limit” thôi

22) Những câu hỏi trong Quiz có một đáp án đúng hay nhiều đáp án đúng?
có thể có nhiều đáp án đúng. Bằng cách thiết lập giá trị Grade của từng đáp án (xem GUI 3.2), ta có thể làm câu có nhiều đáp án. Cụ thể hơn giả sử trong danh sách các đáp án có nhiều hơn 1 đáp án được grade khác None thì có nghĩa câu đó có nhiều đáp án. Tất nhiên khi ấy giao diện chọn câu trả lời phải là radiobox chứ không phải checkbox
23) Nếu sau dòng "ANSWER: " (các chữ cái phải viết hoa) có nhiều hơn 1 dòng trống thì có được coi là dữ liệu đầu vào hợp lệ hay không?
Vẫn được coi là hợp lệ
thầy chưa hiểu bài 7 không có hình ảnh là như nào, có phải ý em là không có giao diện dạng GUI (Graphical user interface) không? Nếu đúng là không có GUI thì điểm tối đa của bài 7 sẽ là 1.5đ. Như vậy sẽ bị mất 2.5đ trên thang 20
24) Ở bài 9, em chưa hiểu chức năng tạo mật khẩu là gì?
Là trước khi tạo file thì phần mềm sẽ hỏi người dùng có muốn đặt mật khẩu cho file không. Nếu có đặt mật khẩu gì đó, như vậy bất kỳ ai muốn mở file thì phải nhập đúng mật khẩu cho họ. Đây là ví dụ của trang tạo pass cho file PDF: https://smallpdf.com/protect-pdf
25) ở bài 7 là nếu như trong câu hỏi ở ngân hàng đề có hình ảnh, mà sản phẩm của em không in ra được thì em sẽ bị mất bao nhiêu điểm?
Sẽ bị mất 0.5 điểm
26) Ở bài 4, đối với các câu hỏi được đọc từ file .txt hoặc file .docx, thì category của chúng sẽ được xác định như nào?
Category cho các câu đó sẽ được chọn ngẫu nhiên từ một trong danh sách các category đã có sẵn. Nếu trong hệ thống chưa có category nào thì hệ thống sẽ phải tạo ra một category có tên ngẫu nhiên (chẳng hạn tên theo định dạng DD-MM-YYYY có nội dung phụ thuộc vào ngày tháng năm hiện tại)
27) Phiên bản 0612 khác gì với phiên bản trước
Bổ sung thêm câu hỏi Q&A số 23, 24, 25, 26

