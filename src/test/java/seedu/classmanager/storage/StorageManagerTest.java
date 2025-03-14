package seedu.classmanager.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.classmanager.testutil.TypicalStudents.getTypicalClassManager;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.classmanager.commons.core.GuiSettings;
import seedu.classmanager.model.ClassManager;
import seedu.classmanager.model.ReadOnlyClassManager;
import seedu.classmanager.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonClassManagerStorage classManagerStorage = new JsonClassManagerStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(classManagerStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void classManagerReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonClassManagerStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonClassManagerStorageTest} class.
         */
        ClassManager original = getTypicalClassManager();
        storageManager.saveClassManager(original);
        ReadOnlyClassManager retrieved = storageManager.readClassManager().get();
        assertEquals(original, new ClassManager(retrieved));
    }

    @Test
    public void getClassManagerFilePath() {
        assertNotNull(storageManager.getClassManagerFilePath());
    }

}
