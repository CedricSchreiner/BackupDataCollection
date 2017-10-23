package exceptions;

/**
 * Created by Cedric on 23.10.2017.
 * Gets thrown if a file type is entered that is not declared in the enum class "FileTypeEnum"
 */
public class FileTypeNotDeclared extends Exception{
    public FileTypeNotDeclared(String iv_message) {
        super(iv_message);
    }
}
