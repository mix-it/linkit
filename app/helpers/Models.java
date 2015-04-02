package helpers;

import com.google.common.base.Function;
import play.db.jpa.Model;

public class Models {

    public static Function<? super Model, Long> toId() {
        return new Function<Model, Long>() {
            @Override
            public Long apply(Model model) {
                return model.getId();
            }
        };
    }
}
