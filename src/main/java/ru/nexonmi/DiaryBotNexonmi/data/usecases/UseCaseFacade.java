package ru.nexonmi.DiaryBotNexonmi.data.usecases;

import org.springframework.stereotype.Service;
import ru.nexonmi.DiaryBotNexonmi.data.answer.DeleteAllDataAnswer;
import ru.nexonmi.DiaryBotNexonmi.data.answer.GetFileDataByFileNameAnswer;
import ru.nexonmi.DiaryBotNexonmi.data.answer.GetListOfFilesAnswer;
@Service
public class UseCaseFacade {

    private final DeleteAllDataUC deleteAllDataUC;
    private final GetListOfFilesUC getListOfAllFilesUC;
    private final GetFileDataByFileNameUC getFileDataByFileNameUC;

    public UseCaseFacade(
            DeleteAllDataUC deleteAllDataUC,
            GetListOfFilesUC getListOfAllFilesUC,
            GetFileDataByFileNameUC getFileDataByFileNameUC
    ) {
        this.deleteAllDataUC = deleteAllDataUC;
        this.getListOfAllFilesUC = getListOfAllFilesUC;
        this.getFileDataByFileNameUC = getFileDataByFileNameUC;
    }

    public DeleteAllDataAnswer deleteAllData(String deleteKey){
        return deleteAllDataUC.deleteAllFiles(deleteKey);
    }

    public GetListOfFilesAnswer getListOfFiles(String accessKey){
        return getListOfAllFilesUC.getListOfFiles(accessKey);
    }

    public GetFileDataByFileNameAnswer getFileDataByFileName(String accessKey, String filename){
        return getFileDataByFileNameUC.getFileDataByFileName(accessKey, filename);
    }
}
