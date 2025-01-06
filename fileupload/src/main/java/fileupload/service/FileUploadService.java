package fileupload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Optional;

@Service
public class FileUploadService {
    private static final String SAVE_PATH = "/Users/day/fileupload-files";
    private static final String URL = "/images";

    public String restore(MultipartFile file) throws RuntimeException {
        try {
            File uploadDirectory = new File(SAVE_PATH);
            if (!uploadDirectory.exists() && !uploadDirectory.mkdirs()) { // 디렉토리가 존재하지 않고 만드는데 실패한 경우
                return null;
            }

            if (file.isEmpty()) {
                return null;
            }

            // Optional
            // null의 위험으로 부터 보호받을 수 있음.
            // 주로 값이 있을 수도 있고 없을 수도 있는 상황에서 사용, 이를 통해 null을 다루는 문제를 좀 더 안전하고 명시적으로 처리

            String originFilename = Optional.ofNullable(file.getOriginalFilename()).orElse("");
            String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1); // 확장자
            String saveFilename = generateSaveFilename(extName);
            long fileSize = file.getSize();

            System.out.println("#####" + originFilename);
            System.out.println("#####" + saveFilename);
            System.out.println("#####" + fileSize);

            byte[] data = file.getBytes();

            OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
            os.write(data);
            os.close();

            return URL + "/" + saveFilename;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateSaveFilename(String extName) {
        Calendar calendar = Calendar.getInstance();
        return "" + calendar.get(Calendar.YEAR)
                + calendar.get(Calendar.MONTH)
                + calendar.get(Calendar.DATE)
                + calendar.get(Calendar.HOUR)
                + calendar.get(Calendar.MINUTE)
                + calendar.get(Calendar.SECOND)
                + ("." + extName);
    }
}
