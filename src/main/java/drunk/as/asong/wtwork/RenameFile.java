package drunk.as.asong.wtwork;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * @ClassName: RenameFile <br/>
 * @Function: TODO ADD FUNCTION. <br/>
 * @Reason: TODO ADD REASON. <br/>
 * @Date: 2020/2/11 2:27 下午<br/>
 * @author: jeff
 * @see
 * @since JDK 1.7-1.8
 * 佛祖保佑,永无BUG
 */
public class RenameFile {

    Map<String, String> covMap = new HashMap<>();
    boolean addCov = true;
    boolean differentiate = true;
    final String yes = "yes";
    final String no = "no";
    String noDiffPath = null;

    public void rename() throws IOException {
        Scanner readers = new Scanner(System.in);
        System.out.println("输入你的原始文件路径后回车：");
        String filePath = readers.nextLine();
        System.out.println("是否需要区分文件夹输出转换后的文件,是输入yes,否输入no 后回车");
        String diffFlag = readers.nextLine();
        if (yes.equals(diffFlag.toLowerCase())) {
            differentiate = true;
        } else if (no.equals(diffFlag.toLowerCase())) {
            differentiate = false;
        } else {
            System.out.println("输入:" + differentiate + "不规范,程序结束");
            return;
        }
        if (!differentiate) {
            noDiffPath = "ALL";
        }
        while (addCov) {
            System.out.println("输入你要转的字母后回车：");
            String firstChar = readers.nextLine();
            System.out.println("输入你上面要转成的字母后回车：");
            String toCov = readers.nextLine();
            covMap.put(firstChar, toCov);
            System.out.println("是否结束：是输入yes,否输入no 后回车");
            String flag = readers.nextLine();
            if (yes.equals(flag.toLowerCase())) {
                addCov = false;
            } else if (no.equals(flag.toLowerCase())) {
                addCov = true;
            } else {
                System.out.println("输入:" + flag + "不规范,程序结束");
                return;
            }
        }


        List<String> allFileName = getAllFileName(filePath);
        if (allFileName == null) {
            return;
        }
        for (String fileName : allFileName) {
            String firstChar = fileName.substring(0, 1);
            String type = fileName.substring(0, 2);
            String sourcePath = filePath + "/" + fileName;
            File sourceFile = new File(sourcePath);
            File destFile = null;
            String toCov = covMap.get(firstChar);
            if (toCov == null) {
                System.out.println("请在程序开始前输入[" + firstChar + "]需要替换的字符,文件名" + fileName);
                return;
            }
            File file = null;
            if (differentiate) {
                String toPath = filePath + "/" + type + "/" + fileName.replaceFirst(firstChar, toCov);
                destFile = new File(toPath);
                file = new File(filePath + "/" + type);
            } else {
                destFile = new File(filePath + "/" + noDiffPath + "/" + fileName.replaceFirst(firstChar, toCov));
                file = new File(filePath + "/" + noDiffPath);
            }

            if (!(file.exists() && file.isDirectory())) {
                file.mkdir();
            }
            Files.copy(sourceFile.toPath(), destFile.toPath());
        }


    }

    public static List<String> getAllFileName(String path) {
        File file = new File(path);
        File[] tempList = file.listFiles();
        if (tempList == null) {
            System.err.println("路径下不存在文件!");
            return null;
        }
        List<String> files = new ArrayList<>();
        for (File subFile : tempList) {
            if (subFile.isFile()) {
                if (subFile.getName().equals(".DS_Store")) {
                    continue;
                }
                files.add(subFile.getName());
            }
        }
        return files;
    }
}
