package pers.hmm.shop.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 胡敏敏
 * @Date 2019/7/8
 * @DESC:
 */
public interface UploadService {
    /**
     * 上传图片
     * @param file
     * @return
     */
    String uploadImage(MultipartFile file);
}
