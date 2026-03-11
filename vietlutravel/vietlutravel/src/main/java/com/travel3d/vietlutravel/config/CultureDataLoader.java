package com.travel3d.vietlutravel.config;

import com.travel3d.vietlutravel.model.Culture;
import com.travel3d.vietlutravel.repository.CultureRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Nạp dữ liệu văn hóa mẫu vào bảng Culture (MySQL) khi bảng đang trống.
 * Chạy sau khi ứng dụng khởi động. Chỉ insert khi chưa có bản ghi nào.
 */
@Component
public class CultureDataLoader implements ApplicationRunner {

    private final CultureRepository cultureRepository;

    public CultureDataLoader(CultureRepository cultureRepository) {
        this.cultureRepository = cultureRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (cultureRepository.count() > 0) {
            return;
        }
        List<Culture> list = List.of(
                culture("Lễ hội Vía Bà Chúa Xứ Núi Sam", "/image/t1.jpg",
                        "Lễ hội tâm linh lớn nhất miền Tây, được UNESCO công nhận Di sản văn hóa phi vật thể (2025).",
                        "https://www.youtube.com/embed/bQAE5eFkCJo",
                        "Lễ hội Vía Bà Chúa Xứ Núi Sam là lễ hội tâm linh lớn nhất miền Tây, được UNESCO công nhận Di sản văn hóa phi vật thể. Diễn ra từ ngày 22 đến 27 tháng 4 âm lịch hàng năm tại miếu Bà Chúa Xứ núi Sam (Châu Đốc, An Giang). Lễ hội thu hút hàng triệu lượt khách hành hương mỗi năm với các nghi thức trang trọng như Lễ phục hiện rước tượng Bà, Lễ Tắm Bà..."),
                culture("Lễ hội Đua bò Bảy Núi", "/image/t2.jpg",
                        "Nét văn hóa độc đáo của đồng bào Khmer, diễn ra dịp Sene Dolta (tháng 8-9 âm lịch), với những cuộc đua kịch tính trên đồng ruộng.",
                        "https://www.youtube.com/embed/sUqJd4rlLf0",
                        "Lễ hội Đua bò Bảy Núi là nét văn hóa độc đáo của đồng bào Khmer vùng Bảy Núi (Thất Sơn), An Giang. Lễ hội thường diễn ra vào dịp lễ Sene Dolta (lễ cúng ông bà) khoảng cuối tháng 8 hoặc đầu tháng 9 âm lịch. Các đôi bò tham gia tranh tài quyết liệt trên đồng ruộng ngập nước, tạo nên không khí sôi động và kịch tính."),
                culture("Văn hóa Thất Sơn (Bảy Núi)", "/image/t3.jpg",
                        "Di sản thiên nhiên - văn hóa hùng vĩ, nơi giao thoa tín ngưỡng đa dân tộc.",
                        "https://www.youtube.com/embed/wuXPRtxie3E",
                        "Văn hóa Thất Sơn (Bảy Núi) là vùng đất linh thiêng và hùng vĩ của An Giang, bao gồm 7 ngọn núi nổi tiếng. Đây là nơi giao thoa của nhiều dòng tín ngưỡng, tôn giáo và văn hóa của các dân tộc Kinh, Khmer, Hoa... Vùng đất này còn lưu truyền nhiều huyền thoại kỳ bí và là điểm đến hấp dẫn cho du khách yêu thích khám phá thiên nhiên và tâm linh."),
                culture("Văn hóa Tín ngưỡng – Tôn giáo", "/image/t4.jpg",
                        "Sự hòa quyện giữa Phật giáo, Công giáo, Hồi giáo và tín ngưỡng dân gian.",
                        "https://www.youtube.com/embed/wuXPRtxie3E",
                        "An Giang là vùng đất đa tôn giáo, nơi hội tụ và chung sống hòa bình của Phật giáo (Bắc tông, Nam tông Khmer, Hòa Hảo), Công giáo, Hồi giáo (Chăm) và các tín ngưỡng dân gian (thờ Bà Chúa Xứ). Sự đa dạng này tạo nên bức tranh văn hóa tâm linh phong phú và độc đáo."),
                culture("Văn hóa Đa dân tộc", "/image/t5.png",
                        "Đồng bào Kinh, Khmer, Chăm, Hoa sống hài hòa, tạo nên bức tranh văn hóa phong phú.",
                        "https://www.youtube.com/embed/jm_fmhtiSqU",
                        "Cộng đồng các dân tộc Kinh, Khmer, Chăm, Hoa đã cùng nhau sinh sống lâu đời tại An Giang, tạo nên sự giao thoa văn hóa đặc sắc trong ẩm thực, kiến trúc, lễ hội và lối sống. Mỗi dân tộc vẫn giữ gìn được bản sắc riêng, góp phần làm phong phú thêm kho tàng văn hóa của tỉnh."),
                culture("Văn hóa Nghệ thuật Dân gian", "/image/t6.jpg",
                        "Đờn ca tài tử, múa lăm vông, hát bội – hồn cốt Nam Bộ.",
                        "https://www.youtube.com/embed/FPOT4Kde--k",
                        "Các loại hình nghệ thuật dân gian như Đờn ca tài tử, hát bội, múa Lăm vông (Khmer), nhạc ngũ âm... vẫn được bảo tồn và phát huy tại An Giang. Đây là món ăn tinh thần không thể thiếu trong các dịp lễ hội, sinh hoạt cộng đồng."),
                culture("Nghề Truyền thống tại An Giang", "/image/t7.jpg",
                        "Dệt thổ cẩm, làm đường thốt nốt, nuôi cá bè – tinh hoa làng nghề.",
                        "https://www.youtube.com/embed/0jcjIhLbs5I",
                        "An Giang nổi tiếng với nhiều làng nghề truyền thống như dệt thổ cẩm (người Chăm, Khmer), rèn, mộc, làm đường thốt nốt, làm mắm... Các sản phẩm thủ công này không chỉ có giá trị kinh tế mà còn mang đậm bản sắc văn hóa địa phương."),
                culture("Nghề Nuôi Cá Bè trên Sông", "/image/t8.jpg",
                        "Nếp sống sông nước đặc trưng miền Tây sông nước.",
                        "https://www.youtube.com/embed/QCt9ID-99VU",
                        "Nghề nuôi cá bè trên sông Hậu, sông Tiền là nét đặc trưng của vùng sông nước An Giang. Những ngôi nhà nổi bập bềnh trên sông, bên dưới là lồng bè nuôi cá, tạo nên khung cảnh độc đáo và là nguồn sống của nhiều hộ gia đình."),
                culture("Làng Chăm Châu Phong – Bản sắc Hồi giáo Nam Bộ", "/image/t9.jpg",
                        "Văn hóa Chăm với nhà sàn, thánh đường và nghề dệt thổ cẩm.",
                        "https://www.youtube.com/embed/l9B2CtnH6VQ",
                        "Làng Chăm Châu Phong (thị xã Tân Châu) lưu giữ những nét văn hóa đặc sắc của cộng đồng người Chăm theo đạo Hồi (Islam). Nổi bật với kiến trúc thánh đường Mubarak, những ngôi nhà sàn gỗ truyền thống và nghề dệt thổ cẩm tinh xảo."),
                culture("Kiến trúc chùa Khmer – Chùa Xvayton", "/image/t10.jpg",
                        "Kiến trúc độc đáo, mái cong, họa tiết tinh xảo của người Khmer.",
                        "https://www.youtube.com/embed/v3iewdRKW6Y",
                        "Chùa Xvayton (Xà Tón) ở Tri Tôn là ngôi chùa Khmer cổ kính và tiêu biểu cho kiến trúc chùa tháp Nam Tông. Ngôi chùa nổi bật với mái cong vút, chạm khắc tinh xảo, thể hiện trình độ nghệ thuật và thẩm mỹ cao của người Khmer."),
                culture("Văn hóa Ẩm thực – Bún cá Châu Đốc", "/image/t11.jpg",
                        "Món đặc sản trứ danh, hương vị đậm đà sông nước.",
                        "https://www.youtube.com/embed/O8cw5pcKtt8",
                        "Bún cá Châu Đốc là món ăn đặc sản nổi tiếng, mang hương vị đậm đà của mắm ruốc, cá lóc đồng và các loại rau sống đặc trưng miền Tây (bông điên điển, rau muống bào...). Món ăn dân dã này đã trở thành thương hiệu ẩm thực của An Giang."),
                culture("Nghề dệt thổ cẩm Tân Châu", "/image/t12.jpg",
                        "Tinh hoa làng nghề, họa tiết truyền thống của người Khmer.",
                        "https://www.youtube.com/embed/-Fek_Ctl18w",
                        "Làng lụa Tân Châu nổi tiếng từ lâu đời với sản phẩm lụa lãnh Mỹ A trứ danh. Quy trình dệt nhuộm thủ công tỉ mỉ bằng trái mặc nưa tạo nên loại lụa đen bóng, mềm mại và mát lạnh, được mệnh danh là 'nữ hoàng của các loại lụa'."),
                culture("Văn hóa Chợ nổi Long Xuyên", "/image/t13.jpg",
                        "Nếp sống sông nước miền Tây, mua bán trên sông tấp nập.",
                        "https://www.youtube.com/embed/RO7BbLnO92I",
                        "Chợ nổi Long Xuyên vẫn giữ được nét nguyên sơ của văn hóa chợ nổi miền Tây. Nơi đây không quá ồn ào xô bồ, người dân buôn bán các loại nông sản, trái cây trên ghe xuồng, tạo nên khung cảnh bình dị và gần gũi."),
                culture("Nghệ thuật Đờn ca tài tử", "/image/t14.jpg",
                        "Hồn dân ca Nam Bộ, di sản UNESCO, sống động ở An Giang.",
                        "https://www.youtube.com/embed/uB6ye79H9C0",
                        "Đờn ca tài tử là loại hình nghệ thuật dân gian đặc sắc, được UNESCO công nhận là Di sản văn hóa phi vật thể đại diện của nhân loại. Tại An Giang, phong trào đờn ca tài tử phát triển mạnh mẽ, là món ăn tinh thần không thể thiếu của người dân."),
                culture("Lễ hội Đua ghe ngo", "/image/t15.jpg",
                        "Niềm tự hào của người Khmer, đua thuyền rồng trên sông nước.",
                        "https://www.youtube.com/embed/crZF3hghbgQ",
                        "Đua ghe ngo là hoạt động thể thao truyền thống sôi nổi nhất trong lễ hội Ok Om Bok của đồng bào Khmer. Những chiếc ghe ngo dài hàng chục mét, được trang trí rực rỡ, lao nhanh trên mặt nước trong tiếng hò reo cổ vũ của hàng ngàn người xem."),
                culture("Lễ hội Chol Chnam Thmay (Tết Khmer)", "/image/t1.jpg",
                        "Tết cổ truyền lớn nhất của đồng bào Khmer, diễn ra tháng 4 dương lịch, với nghi thức tắm Phật, xông nhà, thả chim cá.",
                        "https://www.youtube.com/embed/mBnyZhEc21I",
                        "Chol Chnam Thmay là lễ hội mừng năm mới cổ truyền lớn nhất của đồng bào Khmer Nam Bộ. Diễn ra vào khoảng giữa tháng 4 dương lịch, tết Chol Chnam Thmay kéo dài 3 ngày với nhiều nghi thức ý nghĩa như lễ rước Đại lịch, lễ dâng cơm, lễ tắm Phật, đắp núi cát... cầu mong năm mới mưa thuận gió hòa, mùa màng bội thu."),
                culture("Lễ hội Ok Om Bok (Cúng Trăng)", "/image/t2.jpg",
                        "Lễ cúng trăng rằm tháng 10 âm lịch, thả đèn nước, đua ghe ngo, tri ân thần Mặt Trăng bảo vệ mùa màng.",
                        "https://www.youtube.com/embed/Vq-KN5ANW1I",
                        "Lễ hội Ok Om Bok (còn gọi là Lễ cúng Trăng) diễn ra vào ngày rằm tháng 10 âm lịch hàng năm. Đây là dịp để đồng bào Khmer bày tỏ lòng biết ơn đối với thần Mặt Trăng - vị thần cai quản thời tiết và mùa màng. Lễ hội có nhiều hoạt động đặc sắc như lễ cúng trăng, thả đèn nước và đặc biệt là đua ghe ngo.")
        );
        cultureRepository.saveAll(list);
    }

    private static Culture culture(String title, String thumbnail, String shortDesc, String videoUrl, String fullDesc) {
        Culture c = new Culture();
        c.setTitle(title);
        c.setThumbnail(thumbnail);
        c.setShortDescription(shortDesc);
        c.setVideoUrl(videoUrl);
        c.setFullDescription(fullDesc);
        return c;
    }
}
