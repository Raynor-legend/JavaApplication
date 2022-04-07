-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2020 at 04:53 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `cthd`
--

CREATE TABLE `cthd` (
  `STT` int(11) NOT NULL,
  `MAHD` int(11) NOT NULL,
  `MASACH` varchar(10) NOT NULL,
  `TENSACH` varchar(200) NOT NULL,
  `DONGIABAN` int(10) NOT NULL,
  `SOLUONG` int(10) NOT NULL,
  `THANHTIEN` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cthd`
--

INSERT INTO `cthd` (`STT`, `MAHD`, `MASACH`, `TENSACH`, `DONGIABAN`, `SOLUONG`, `THANHTIEN`) VALUES
(1, 500002, 'SGK03', 'Gỉai tích', 20000, 1, 20000),
(2, 500002, 'VH02', 'Chiếc lá cuối cùng', 50000, 2, 100000),
(3, 500003, 'VH02', 'Chiếc lá cuối cùng', 50000, 2, 100000),
(4, 500003, 'VH03', 'Chiếc thuyền ngoài xa', 25000, 2, 50000),
(5, 500004, 'SGK03', 'Gỉai tích', 20000, 2, 40000),
(6, 500004, 'VH03', 'Chiếc thuyền ngoài xa', 25000, 2, 50000),
(7, 500005, 'SGK02', 'Xác suất thống kê', 100000, 3, 300000),
(8, 500005, 'VH03', 'Chiếc thuyền ngoài xa', 25000, 1, 25000),
(9, 500006, 'DM05', 'Dấu vết', 200000, 1, 200000),
(10, 500007, 'DM05', 'Dấu vết', 200000, 4, 800000),
(11, 500007, 'SGK01', 'Toán cao cấp(Tham Khảo)', 50000, 5, 250000),
(12, 500008, 'VH01', 'Truyện Kiều', 500000, 4, 2000000),
(13, 500008, 'VH02', 'Chiếc lá cuối cùng', 50000, 5, 250000),
(14, 500009, 'SGK01', 'Toán cao cấp(Tham Khảo)', 50000, 1, 50000),
(15, 500009, 'VH01', 'Truyện Kiều', 500000, 2, 1000000),
(16, 500010, 'VH01', 'Truyện Kiều', 500000, 2, 1000000),
(17, 500011, 'VH02', 'Chiếc lá cuối cùng', 50000, 10, 500000),
(18, 500011, 'SGK02', 'Xác suất thống kê', 100000, 2, 200000),
(19, 500012, 'VH03', 'Chiếc thuyền ngoài xa', 25000, 3, 75000),
(20, 500013, 'SGK03', 'Gỉai tích', 20000, 2, 40000),
(21, 500013, 'VH01', 'Truyện Kiều', 500000, 1, 500000),
(22, 500014, 'fjhwt87y', 'Tiếng 1', 456, 1, 456),
(23, 500015, 'DM05', 'Dấu vết', 200000, 5, 1000000),
(24, 500016, 'VH02', 'Chiếc lá cuối cùng', 50000, 5, 250000),
(25, 500017, 'DM05', 'Dấu vết', 200000, 5, 1000000),
(26, 500018, 'VH02', 'Chiếc lá cuối cùng', 50000, 1, 50000),
(27, 500019, 'VH03', 'Chiếc thuyền ngoài xa', 25000, 1, 25000),
(28, 500021, 'VH02', 'Chiếc lá cuối cùng', 50000, 2, 100000),
(29, 500021, 'SGK02', 'Xác suất thống kê', 100000, 1, 100000),
(30, 500022, 'SGK02', 'Xác suất thống kê', 100000, 3, 300000),
(31, 500022, 'SGK01', 'Toán cao cấp(Tham Khảo)', 50000, 1, 50000),
(32, 500023, 'VH03', 'Chiếc thuyền ngoài xa', 25000, 4, 100000),
(33, 500023, 'SGK01', 'Toán cao cấp(Tham Khảo)', 50000, 1, 50000),
(34, 500023, 'SGK03', 'Gỉai tích', 20000, 1, 20000),
(35, 500024, 'VH01', 'Truyện Kiều', 500000, 4, 2000000),
(36, 500024, 'SGK02', 'Xác suất thống kê', 100000, 1, 100000),
(37, 500025, 'VH01', 'Truyện Kiều', 500000, 1, 500000),
(38, 500025, 'SGK03', 'Gỉai tích', 20000, 1, 20000),
(39, 500025, 'fgfhjk', 'dfghjk', 5465465, 2, 10930930),
(40, 500026, 'VH01', 'Truyện Kiều', 500000, 4, 2000000),
(41, 500026, 'SGK03', 'Gỉai tích', 20000, 1, 20000),
(42, 500026, 'VH02', 'Chiếc lá cuối cùng', 50000, 1, 50000),
(43, 500027, 'SGK02', 'Xác suất thống kê', 100000, 2, 200000),
(44, 500027, 'fjhwt87y', 'Tiếng 1', 456, 5, 2280),
(45, 500028, 'SGK02', 'Xác suất thống kê', 100000, 1, 100000),
(46, 500028, 'SGK03', 'Gỉai tích', 20000, 1, 20000);

-- --------------------------------------------------------

--
-- Table structure for table `cthdnhap`
--

CREATE TABLE `cthdnhap` (
  `STT` int(11) NOT NULL,
  `MAPHIEUNHAP` varchar(10) NOT NULL,
  `MASACH` varchar(10) NOT NULL,
  `TENSACH` varchar(30) NOT NULL,
  `SOLUONG` int(10) NOT NULL,
  `DONGIANHAP` int(10) NOT NULL,
  `THANHTIEN` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cthdnhap`
--

INSERT INTO `cthdnhap` (`STT`, `MAPHIEUNHAP`, `MASACH`, `TENSACH`, `SOLUONG`, `DONGIANHAP`, `THANHTIEN`) VALUES
(1, '9000002', 'VH03', 'Chiếc thuyền ngoài xa', 1000, 2, 2000),
(2, '9000003', 'VH03', 'Chiếc thuyền ngoài xa', 1000, 2, 2000),
(3, '9000003', 'SGK03', 'Gỉai tích', 1000, 2, 2000),
(4, '9000003', 'fjhwt87y', 'Tiếng 1', 50000, 50, 2500000),
(5, '9000004', 'VH01', 'Truyện Kiều', 2000, 4, 8000),
(6, '9000005', 'VH01', 'Truyện Kiều', 2000, 3, 6000);

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `MAHD` int(15) NOT NULL,
  `MAKH` varchar(10) DEFAULT NULL,
  `MANV` varchar(10) NOT NULL,
  `NGAYLAP` date NOT NULL,
  `THANHTIEN` int(10) NOT NULL,
  `TRANGTHAI` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`MAHD`, `MAKH`, `MANV`, `NGAYLAP`, `THANHTIEN`, `TRANGTHAI`) VALUES
(500000, 'KH01', 'NV005', '2020-06-19', 520000, 0),
(500002, 'KH01', 'NV005', '2020-06-19', 120000, 1),
(500003, 'KH01', 'NV005', '2020-06-19', 150000, 1),
(500004, 'KH01', 'NV005', '2020-06-19', 90000, 1),
(500005, '0', 'NV005', '2020-06-19', 325000, 0),
(500006, '0', 'NV005', '2020-06-19', 200000, 0),
(500007, '0', 'NV005', '2020-06-19', 1050000, 1),
(500008, 'KH03', 'NV005', '2020-06-19', 2250000, 0),
(500009, 'KH01', 'NV005', '2020-06-19', 1050000, 1),
(500010, '0', 'NV005', '2020-06-19', 1000000, 0),
(500011, '0', 'NV005', '2020-06-19', 700000, 1),
(500012, '0', 'NV005', '2020-06-19', 75000, 1),
(500013, '0', 'NV005', '2020-06-19', 540000, 1),
(500014, '0', 'NV005', '2020-06-19', 456, 1),
(500015, '0', 'NV005', '2020-06-19', 1000000, 1),
(500016, '0', 'NV005', '2020-06-19', 2250000, 1),
(500017, '0', 'NV005', '2020-06-20', 1000000, 1),
(500018, '0', 'NV005', '2020-06-21', 50000, 1),
(500019, '0', 'NV005', '2020-06-21', 25000, 1),
(500020, '0', 'NV005', '2020-06-21', 25000, 1),
(500021, '0', 'nv005', '2020-06-22', 200000, 1),
(500022, '0', 'nv005', '2020-06-22', 350000, 1),
(500023, '0', 'nv005', '2020-06-22', 170000, 1),
(500024, '0', 'nv005', '2020-06-22', 2100000, 1),
(500025, '0', 'nv005', '2020-06-22', 11450930, 1),
(500026, '0', 'nv005', '2020-06-22', 2070000, 1),
(500027, '0', 'nv005', '2020-06-22', 202280, 1),
(500028, '0', 'nv005', '2020-06-23', 120000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `MAKH` varchar(10) NOT NULL,
  `HOKH` varchar(15) NOT NULL,
  `TENKH` varchar(15) NOT NULL,
  `NGAYSINH` date NOT NULL,
  `GIOITINH` varchar(10) NOT NULL,
  `DIACHI` varchar(50) NOT NULL,
  `SDT` varchar(11) NOT NULL,
  `Mail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`MAKH`, `HOKH`, `TENKH`, `NGAYSINH`, `GIOITINH`, `DIACHI`, `SDT`, `Mail`) VALUES
('0', '', '', '2020-06-01', '', '', '', ''),
('KH01', 'Nguyễn', 'Như', '2000-01-24', 'Nữ', '157 Nguyễn Biểu, P1,Q5,HCM', '0352005356', 'nguyenngocquynhnhu24012000@gmail.com'),
('KH02', 'Lâm', 'Như', '1999-09-08', 'Nữ', '237 An Dương Vương', '0354667567', 'quynhnhu@gmail.com'),
('KH03', 'Nguyễn', 'Nhung', '1998-06-02', 'Nữ', '27 Nguyễn Trãi', '0323445098', 'nhung@gmail.com'),
('KH04', 'Phan', 'Oanh', '1999-09-27', 'Nữ', '109 Dương Bá Trạc, P1,Q8,HCM', '0978556341', 'oanhphan@gmail.com'),
('KH05', 'Lâm', 'Tài', '2000-02-14', 'Nam', '176 Nguyễn Văn Cừ', '0908675222', 'lamtai@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `MANCC` varchar(10) NOT NULL,
  `TENNCC` varchar(30) NOT NULL,
  `DIACHI` varchar(255) NOT NULL,
  `SDT` varchar(255) NOT NULL,
  `GMAIL` varchar(255) NOT NULL,
  `TRANGTHAI` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`MANCC`, `TENNCC`, `DIACHI`, `SDT`, `GMAIL`, `TRANGTHAI`) VALUES
('HHT', 'Hoa Học Trò', '273 Nguyễn Biểu', '0352005367', 'nguyenngocquynhnhu24012000@gmail.com', 1),
('PN', 'Phương Nam', '275 An Dương Vương phường 3 quận 5', '0251675878', 'phuongnam123@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MANV` varchar(10) NOT NULL,
  `HONV` varchar(20) NOT NULL,
  `TENNV` varchar(20) NOT NULL,
  `NGAYSINH` varchar(15) NOT NULL,
  `GIOITINH` varchar(10) NOT NULL,
  `CMND` varchar(12) NOT NULL,
  `DIACHI` varchar(100) NOT NULL,
  `SDT` varchar(11) NOT NULL,
  `TRANGTHAI` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`MANV`, `HONV`, `TENNV`, `NGAYSINH`, `GIOITINH`, `CMND`, `DIACHI`, `SDT`, `TRANGTHAI`) VALUES
('Hàn', 'Lê', 'Quỳnh Như', '2020-06-11', 'Nam', '272798789', '56 Nguyễn Trãi', '0145665567', 1),
('NV001', 'Nguyễn', 'Như', '2000-01-24', 'Nữ', '272707383', '48/38 Nguyễn Biểu', '0352005356', 1),
('NV002', 'Lâm', 'Phát', '1997-03-15', 'Nam', '272709678', '456 Trần Hưng Đạo,phường 7 ,quận 5', '0789556345', 1),
('NV003', 'Lâm', 'Tài', '1990-02-21', 'Nam', '343456789', ' 67 Nguyễn Tri Phương,phường 7 ,quận 8', '0647889234', 1),
('NV004', 'Lê', 'Yến', '1999-09-15', 'Nữ', '454567890', '457 Hùng Vương phường 3 quận 1', '0356777567', 1),
('NV005', 'Nguyễn', 'Nhi', '2000-09-15', 'Nữ', '232345998', '76 Nguyễn Trãi phường 3 quận5', '0352005356', 1),
('NV006', 'Trương', 'Thanh', '2000-07-10', 'Nữ', '282867456', '68 Nguyễn Biểu phường 1 quận 5', '0789556345', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhapsach`
--

CREATE TABLE `nhapsach` (
  `MANHAP` varchar(10) NOT NULL,
  `MANV` varchar(20) NOT NULL,
  `MANCC` varchar(10) NOT NULL,
  `NGAYNHAP` date NOT NULL,
  `TONGTIEN` float NOT NULL,
  `TRANGTHAI` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhapsach`
--

INSERT INTO `nhapsach` (`MANHAP`, `MANV`, `MANCC`, `NGAYNHAP`, `TONGTIEN`, `TRANGTHAI`) VALUES
('001', 'NV005', 'PN', '2019-05-31', 150000, 1),
('9000002', 'nv002', 'HHT', '2020-06-22', 2000, 1),
('9000003', 'nv002', 'HHT', '2020-06-22', 2504000, 1),
('9000004', 'nv002', 'PN', '2020-06-23', 8000, 1),
('9000005', 'nv002', 'PN', '2020-06-23', 6000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhaxuatban`
--

CREATE TABLE `nhaxuatban` (
  `MANXB` varchar(10) NOT NULL,
  `TENNXB` varchar(30) NOT NULL,
  `DIACHI` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhaxuatban`
--

INSERT INTO `nhaxuatban` (`MANXB`, `TENNXB`, `DIACHI`) VALUES
('HN', 'Hà Nội', '23 Cầu Dừa'),
('TG', 'Thế Gioi', '726 Nguyễn Trãi'),
('TP', 'Thành Phố HCM', '273 An Dương Vương');

-- --------------------------------------------------------

--
-- Table structure for table `sach`
--

CREATE TABLE `sach` (
  `MASACH` varchar(10) NOT NULL,
  `TENSACH` varchar(30) NOT NULL,
  `maTG` varchar(10) NOT NULL,
  `MATL` varchar(10) NOT NULL,
  `MANXB` varchar(10) NOT NULL,
  `SOLUONG` int(10) NOT NULL,
  `DONGIABAN` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sach`
--

INSERT INTO `sach` (`MASACH`, `TENSACH`, `maTG`, `MATL`, `MANXB`, `SOLUONG`, `DONGIABAN`) VALUES
('DM05', 'Dấu vết', 'LP', 'TT', 'HN', 84, 200000),
('DM91', 'Sasch nef', 'LP', 'NT', 'HN', 0, 100000),
('fgfhjk', 'dfghjk', 'HC', 'NT', 'HN', 4460, 5465465),
('fjhgtiuj', 'Tam', 'HC', 'NT', 'HN', 0, 54444),
('fjhwt87y', 'Tiếng 1', 'HC', 'NT', 'HN', 45, 456),
('SGK01', 'Toán cao cấp(Tham Khảo)', 'ML', 'SGK', 'HN', 182, 50000),
('SGK02', 'Xác suất thống kê', 'ML', 'SGK', 'TG', 94, 100000),
('SGK03', 'Gỉai tích', 'ML', 'SGK', 'TG', 36, 20000),
('SM09', 'Sách mới', 'HN', 'NT', 'HN', 20, 50000),
('VH01', 'Truyện Kiều', 'HC', 'VH', 'TG', 130, 500000),
('VH02', 'Chiếc lá cuối cùng', 'HC', 'VH', 'TG', 210, 50000),
('VH03', 'Chiếc thuyền ngoài xa', 'QN', 'VH', 'TP', 94, 25000);

-- --------------------------------------------------------

--
-- Table structure for table `tacgia`
--

CREATE TABLE `tacgia` (
  `MATG` varchar(10) NOT NULL,
  `TENTG` varchar(30) NOT NULL,
  `TRANGTHAI` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tacgia`
--

INSERT INTO `tacgia` (`MATG`, `TENTG`, `TRANGTHAI`) VALUES
('HC', 'Huy Cận', 0),
('HN', 'Hà Anh', 1),
('LP', 'Tấn Phong', 1),
('ML', 'Lê Minh Loan', 1),
('NN', 'Nhu Ngoc', 0),
('QN', 'Quỳnh Như', 1),
('TH', 'Tô Hoài', 1),
('VD', 'Ngô văn Đồng', 1);

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MANV` varchar(10) NOT NULL,
  `PASS` varchar(15) NOT NULL,
  `QUYEN` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`MANV`, `PASS`, `QUYEN`) VALUES
('NV001', '123', 'admin'),
('NV002', '222', 'QL'),
('NV003', '123', 'NV'),
('NV005', '456', 'NV');

-- --------------------------------------------------------

--
-- Table structure for table `theloai`
--

CREATE TABLE `theloai` (
  `MATL` varchar(10) NOT NULL,
  `TENTL` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `theloai`
--

INSERT INTO `theloai` (`MATL`, `TENTL`) VALUES
('NT', 'Ngôn tình'),
('SGK', 'Sách giáo khoa'),
('T', 'Truyện'),
('TC', 'Tạp chí'),
('TT', 'Trinh tham'),
('VH', 'Văn học');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cthd`
--
ALTER TABLE `cthd`
  ADD PRIMARY KEY (`STT`),
  ADD KEY `CTHD` (`MAHD`),
  ADD KEY `HDS` (`MASACH`);

--
-- Indexes for table `cthdnhap`
--
ALTER TABLE `cthdnhap`
  ADD PRIMARY KEY (`STT`),
  ADD KEY `CTPN` (`MAPHIEUNHAP`),
  ADD KEY `SN` (`MASACH`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MAHD`),
  ADD KEY `HDKH` (`MAKH`),
  ADD KEY `NVHD` (`MANV`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MAKH`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`MANCC`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MANV`);

--
-- Indexes for table `nhapsach`
--
ALTER TABLE `nhapsach`
  ADD PRIMARY KEY (`MANHAP`),
  ADD KEY `NCC` (`MANCC`);

--
-- Indexes for table `nhaxuatban`
--
ALTER TABLE `nhaxuatban`
  ADD PRIMARY KEY (`MANXB`);

--
-- Indexes for table `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`MASACH`),
  ADD KEY `maTG` (`maTG`),
  ADD KEY `MATL` (`MATL`),
  ADD KEY `MANXB` (`MANXB`);

--
-- Indexes for table `tacgia`
--
ALTER TABLE `tacgia`
  ADD PRIMARY KEY (`MATG`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MANV`);

--
-- Indexes for table `theloai`
--
ALTER TABLE `theloai`
  ADD PRIMARY KEY (`MATL`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cthd`
--
ALTER TABLE `cthd`
  MODIFY `STT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `cthdnhap`
--
ALTER TABLE `cthdnhap`
  MODIFY `STT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cthd`
--
ALTER TABLE `cthd`
  ADD CONSTRAINT `CTHD` FOREIGN KEY (`MAHD`) REFERENCES `hoadon` (`MAHD`),
  ADD CONSTRAINT `HDS` FOREIGN KEY (`MASACH`) REFERENCES `sach` (`MASACH`);

--
-- Constraints for table `cthdnhap`
--
ALTER TABLE `cthdnhap`
  ADD CONSTRAINT `CTPN` FOREIGN KEY (`MAPHIEUNHAP`) REFERENCES `nhapsach` (`MANHAP`),
  ADD CONSTRAINT `SN` FOREIGN KEY (`MASACH`) REFERENCES `sach` (`MASACH`);

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `HDKH` FOREIGN KEY (`MAKH`) REFERENCES `khachhang` (`MAKH`),
  ADD CONSTRAINT `NVHD` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`);

--
-- Constraints for table `nhapsach`
--
ALTER TABLE `nhapsach`
  ADD CONSTRAINT `NCC` FOREIGN KEY (`MANCC`) REFERENCES `nhacungcap` (`MANCC`);

--
-- Constraints for table `sach`
--
ALTER TABLE `sach`
  ADD CONSTRAINT `sach_ibfk_1` FOREIGN KEY (`maTG`) REFERENCES `tacgia` (`MATG`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `TKNV` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
