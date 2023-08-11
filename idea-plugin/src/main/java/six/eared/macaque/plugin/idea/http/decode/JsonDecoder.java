package six.eared.macaque.plugin.idea.http.decode;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;

public class JsonDecoder<T> implements ResponseDecoder<T> {

    private static final Gson GSON = new Gson();

    private static final StringDecoder DECODER = new StringDecoder();

    private Class<T> clazz;

    public JsonDecoder(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T decode(byte[] bytes) {
        String json = DECODER.decode(bytes);
        return StringUtils.isBlank(json) ? null : GSON.fromJson(json, clazz);
    }
}
