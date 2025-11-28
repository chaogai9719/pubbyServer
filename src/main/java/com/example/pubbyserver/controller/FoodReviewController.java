package com.example.pubbyserver.controller;

import com.example.pubbyserver.annotation.OperationLog;
import com.example.pubbyserver.entity.FoodReview;
import com.example.pubbyserver.service.FoodReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/food-reviews")
public class FoodReviewController {

    @Autowired
    private FoodReviewService foodReviewService;

    @Value("${food.picture.path}")
    private String foodPicturePath;

    /**
     * 根据ID获取美食点评详情
     * @param id 点评ID
     * @return 美食点评信息
     */
    @GetMapping("/{id}")
    public FoodReview getFoodReviewById(@PathVariable Long id) {
        return foodReviewService.getFoodReviewById(id);
    }

    /**
     * 获取美食点评列表（支持按评分筛选）
     * @param rating 评分（可选）
     * @return 美食点评列表
     */
    @GetMapping
    public List<FoodReview> getFoodReviewList(@RequestParam(required = false) Integer rating) {
        return foodReviewService.getFoodReviewList(rating);
    }

    /**
     * 创建新的美食点评
     * @param foodReview 美食点评信息
     * @return 是否成功
     */
    @PostMapping
    @OperationLog(module = "美食评价", type = "INSERT")
    public boolean createFoodReview(@RequestBody FoodReview foodReview) {
        return foodReviewService.createFoodReview(foodReview);
    }

    /**
     * 更新美食点评信息
     * @param id 点评ID
     * @param foodReview 美食点评信息
     * @return 是否成功
     */
    @PutMapping("/{id}")
    @OperationLog(module = "美食评价", type = "UPDATE")
    public boolean updateFoodReview(@PathVariable Long id, @RequestBody FoodReview foodReview) {
        foodReview.setId(id);
        return foodReviewService.updateFoodReview(foodReview);
    }

    /**
     * 删除美食点评
     * @param id 点评ID
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    @OperationLog(module = "美食评价", type = "DELETE")
    public boolean deleteFoodReview(@PathVariable Long id) {
        return foodReviewService.deleteFoodReview(id);
    }

    /**
     * 上传食物图片
     *
     * @param file 图片文件
     * @return 图片访问路径
     * @throws IOException IO异常
     */
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadFoodImage(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("请选择一个文件");
        }

        // 获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            return ResponseEntity.badRequest().body("文件名不能为空");
        }

        // 获取文件扩展名
        String fileExtension = "";
        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < originalFilename.length() - 1) {
            fileExtension = originalFilename.substring(dotIndex);
        }

        // 使用毫秒时间戳生成新文件名
        String newFileName = System.currentTimeMillis() + fileExtension;

        // 创建目标文件
        File destFile = new File(foodPicturePath, newFileName);

        // 确保目标目录存在
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

        // 保存文件
        file.transferTo(destFile);

        return ResponseEntity.ok(newFileName);
    }

    /**
     * 下载食物图片
     *
     * @param imageName 图片名称
     * @return 图片文件
     * @throws IOException IO异常
     */
    @GetMapping("/image/{imageName}")
    public ResponseEntity<FileSystemResource> downloadFoodImage(@PathVariable String imageName) throws IOException {

        // 构建文件路径
        Path filePath = Paths.get(foodPicturePath, imageName);
        File file = filePath.toFile();

        // 检查文件是否存在
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }

        // 确定文件的媒体类型
        String contentType = Files.probeContentType(filePath);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + imageName + "\"");

        FileSystemResource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}