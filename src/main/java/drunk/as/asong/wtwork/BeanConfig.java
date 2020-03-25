package drunk.as.asong.wtwork;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: BeanConfig <br/>
 * @Function: TODO ADD FUNCTION. <br/>
 * @Reason: TODO ADD REASON. <br/>
 * @Date: 2019-12-04 11:38<br/>
 * @author: jeff
 * @see
 * @since JDK 1.7-1.8
 * 佛祖保佑,永无BUG
 */
@Configuration
public class BeanConfig {

    @Bean(initMethod = "rename")
    public RenameFile renameFile(){
        return new RenameFile();
    }

}
