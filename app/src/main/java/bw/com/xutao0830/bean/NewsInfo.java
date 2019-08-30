package bw.com.xutao0830.bean;

import java.util.List;

public class NewsInfo {

    private List<ResultInfo> result;

    public List<ResultInfo> getResult() {
        return result;
    }

    public void setResult(List<ResultInfo> result) {
        this.result = result;
    }

    public class ResultInfo{

        private String imageUrl;
        private String summary;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }

}
