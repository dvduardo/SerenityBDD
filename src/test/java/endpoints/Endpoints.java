package endpoints;

public enum Endpoints {

    Get_resources("/posts/{id}"),

    Post_to("/posts"),

    Patch_to("/posts/{id}"),

    Delete_from("/posts/{id}");



    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
