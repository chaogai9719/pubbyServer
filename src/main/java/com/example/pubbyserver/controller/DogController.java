package com.example.pubbyserver.controller;

import com.example.pubbyserver.annotation.OperationLog;
import com.example.pubbyserver.entity.Dog;
import com.example.pubbyserver.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/dogs")
public class DogController {

    @Autowired
    private DogService dogService;

    @Value("${dog.picture.path}")
    private String dogPicturePath;

    /**
     * 根据ID获取小狗记录详情
     * @param id 记录ID
     * @return 小狗记录信息
     */
    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable Long id) {
        return dogService.getDogById(id);
    }

    /**
     * 获取所有小狗记录
     * @return 小狗记录列表
     */
    @GetMapping
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    /**
     * 创建新的小狗记录
     * @param dog 小狗记录信息
     * @return 是否成功
     */
    @PostMapping
    @OperationLog(module = "小狗记录", type = "INSERT")
    public boolean createDog(@RequestBody Dog dog) {
        return dogService.createDog(dog);
    }

    /**
     * 更新小狗记录信息
     * @param id 记录ID
     * @param dog 小狗记录信息
     * @return 是否成功
     */
    @PutMapping("/{id}")
    @OperationLog(module = "小狗记录", type = "UPDATE")
    public boolean updateDog(@PathVariable Long id, @RequestBody Dog dog) {
        dog.setId(id);
        return dogService.updateDog(dog);
    }

    /**
     * 删除小狗记录
     * @param id 记录ID
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    @OperationLog(module = "小狗记录", type = "DELETE")
    public boolean deleteDog(@PathVariable Long id) {
        return dogService.deleteDog(id);
    }

    /**
     * 上传小狗图片
     *
     * @param file 图片文件
     * @return 图片访问路径
     * @throws IOException IO异常
     */
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadDogImage(@RequestParam("file") MultipartFile file) throws IOException {
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
        File destFile = new File(dogPicturePath, newFileName);

        // 确保目标目录存在
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

        // 保存文件
        file.transferTo(destFile);

        return ResponseEntity.ok(newFileName);
    }

    /**
     * 下载小狗图片
     *
     * @param imageName 图片名称
     * @return 图片文件
     * @throws IOException IO异常
     */
    @GetMapping("/image/{imageName}")
    public ResponseEntity<FileSystemResource> downloadDogImage(@PathVariable String imageName) throws IOException {

        // 构建文件路径
        Path filePath = Paths.get(dogPicturePath, imageName);
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