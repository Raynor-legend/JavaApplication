-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2022 at 02:37 AM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.8

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
  `MASP` varchar(10) NOT NULL,
  `TENSP` varchar(200) NOT NULL,
  `DONGIABAN` int(10) NOT NULL,
  `SOLUONG` int(10) NOT NULL,
  `THANHTIEN` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cthd`
--

INSERT INTO `cthd` (`STT`, `MAHD`, `MASP`, `TENSP`, `DONGIABAN`, `SOLUONG`, `THANHTIEN`) VALUES
(1, 500000, 'fjhwt87y', 'Tiếng 1', 456, 1, 456),
(2, 500002, 'SGK01', 'Toán cao cấp(Tham Khảo)', 50000, 2, 100000);

-- --------------------------------------------------------

--
-- Table structure for table `cthdnhap`
--

CREATE TABLE `cthdnhap` (
  `STT` int(11) NOT NULL,
  `MAPHIEUNHAP` varchar(10) NOT NULL,
  `MASP` varchar(10) NOT NULL,
  `TENSP` varchar(30) NOT NULL,
  `SOLUONG` int(10) NOT NULL,
  `DONGIANHAP` int(10) NOT NULL,
  `THANHTIEN` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cthdnhap`
--

INSERT INTO `cthdnhap` (`STT`, `MAPHIEUNHAP`, `MASP`, `TENSP`, `SOLUONG`, `DONGIANHAP`, `THANHTIEN`) VALUES
(1, '9000002', 'VH03', 'coca1', 1000, 2, 2000),
(2, '9000003', 'VH03', 'coca2', 1000, 2, 2000),
(3, '9000003', 'SGK03', 'coca3', 1000, 2, 2000),
(4, '9000006', 'DM05', 'Dấu vết', 1, 1, 1);

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
(500000, '0', 'nv003', '2022-05-02', 456, 1),
(500002, '0', 'nv003', '2022-05-02', 100000, 1);

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
('KH01', 'Nguyễn', 'Như', '2000-01-24', 'Nữ', '157 Nguyễn Biểu, P1,Q5,HCM', '0352005356', 'nguyenngocquynhnhu24012000@gmail.com'),
('KH02', 'Lâm', 'Như', '1999-09-08', 'Nữ', '237 An Dương Vương', '0354667567', 'quynhnhu@gmail.com'),
('KH03', 'Nguyễn', 'Nhung', '1998-06-02', 'Nữ', '27 Nguyễn Trãi', '0323445098', 'nhung@gmail.com'),
('KH04', 'Phan', 'Oanh', '1999-09-27', 'Nữ', '109 Dương Bá Trạc, P1,Q8,HCM', '0978556341', 'oanhphan@gmail.com'),
('KH05', 'Lâm', 'Tài', '2000-02-14', 'Nam', '176 Nguyễn Văn Cừ', '0908675222', 'lamtai@gmail.com'),
('KH06', 'czx', 'czxcz', '2022-05-04', 'nu', 'gcbgcbcb', '0123456789', 'hvhv@gmail.com');

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
('8888', 'cxvxcv', 'cxvxv', '0123456789', 'nbmb@gmail.com', 1),
('HHT', 'Hoa Học Trò', '273 Nguyễn Biểu', '0352005367', 'nguyenngocquynhnhu24012000@gmail.com', 1);

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
('NV006', 'Trương', 'Thanh', '2000-07-10', 'Nữ', '282867456', '68 Nguyễn Biểu phường 1 quận 5', '0789556345', 1),
('NV007', 'xxzczxc', 'cxczxcz', '2022-05-11', 'Nam', '123456789', 'cxzczxcz', '0123456789', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nhapsp`
--

CREATE TABLE `nhapsp` (
  `MANHAP` varchar(10) NOT NULL,
  `MANV` varchar(20) NOT NULL,
  `MANCC` varchar(10) NOT NULL,
  `NGAYNHAP` date NOT NULL,
  `TONGTIEN` float NOT NULL,
  `TRANGTHAI` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhapsp`
--

INSERT INTO `nhapsp` (`MANHAP`, `MANV`, `MANCC`, `NGAYNHAP`, `TONGTIEN`, `TRANGTHAI`) VALUES
('001', 'NV005', 'HHT', '2019-05-31', 150000, 1),
('9000002', 'nv002', 'HHT', '2020-06-22', 2000, 1),
('9000003', 'nv002', 'HHT', '2020-06-22', 2504000, 1),
('9000004', 'nv002', 'HHT', '2020-06-23', 8000, 1),
('9000005', 'nv002', 'HHT', '2020-06-23', 6000, 1),
('9000006', 'NV002', 'HHT', '2022-05-02', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `MASP` varchar(10) NOT NULL,
  `TENSP` varchar(30) NOT NULL,
  `MATL` varchar(10) NOT NULL,
  `SOLUONG` int(10) NOT NULL,
  `DONGIABAN` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`MASP`, `TENSP`, `MATL`, `SOLUONG`, `DONGIABAN`) VALUES
('1', 'BVBN', 'CF', 0, 12),
('12', 'vhhjghg', 'GK', 3, 55),
('DM05', 'Dấu vết', 'TT', 75, 200000),
('DM91', 'Sasch nef', 'NT', 0, 100000),
('fgfhjk', 'dfghjk', 'NT', 4399, 5465465),
('fjhgtiuj', 'Tam', 'NT', 0, 54444),
('fjhwt87y', 'Tiếng 1', 'NT', 43, 456),
('SGK01', 'Toán cao cấp(Tham Khảo)', 'SGK', 180, 50000),
('SGK02', 'Xác suất thống kê', 'SGK', 94, 100000),
('SGK03', 'Gỉai tích', 'SGK', 40, 20000),
('SM09', 'Sách mới', 'NT', 20, 50000),
('VH01', 'Truyện Kiều', 'VH', 130, 500000),
('VH02', 'Chiếc lá cuối cùng', 'VH', 210, 50000),
('VH03', 'Chiếc thuyền ngoài xa', 'VH', 94, 25000);

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
('NV003', '123', 'NV');

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
('CF', 'Cà phê'),
('GK', 'Giải khát'),
('ST', 'Sinh tố');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cthd`
--
ALTER TABLE `cthd`
  ADD PRIMARY KEY (`STT`),
  ADD KEY `CTHD` (`MAHD`),
  ADD KEY `HDS` (`MASP`);

--
-- Indexes for table `cthdnhap`
--
ALTER TABLE `cthdnhap`
  ADD PRIMARY KEY (`STT`),
  ADD KEY `CTPN` (`MAPHIEUNHAP`),
  ADD KEY `SN` (`MASP`);

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
-- Indexes for table `nhapsp`
--
ALTER TABLE `nhapsp`
  ADD PRIMARY KEY (`MANHAP`),
  ADD KEY `NCC` (`MANCC`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MASP`),
  ADD KEY `MATL` (`MATL`);

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
  ADD CONSTRAINT `HDS` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`);

--
-- Constraints for table `cthdnhap`
--
ALTER TABLE `cthdnhap`
  ADD CONSTRAINT `CTPN` FOREIGN KEY (`MAPHIEUNHAP`) REFERENCES `nhapsp` (`MANHAP`),
  ADD CONSTRAINT `SN` FOREIGN KEY (`MASP`) REFERENCES `sanpham` (`MASP`);

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `TKNV` FOREIGN KEY (`MANV`) REFERENCES `nhanvien` (`MANV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
