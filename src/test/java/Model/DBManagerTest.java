package Model;

import main.java.Model.DBManager;
import main.java.Model.Library;
import main.java.Model.LibraryDatabase;
import main.java.View.Table;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DBManagerTest {

    @Mock private LibraryDatabase mockDataSource;
    @Mock private Connection mockConn;
    @Mock private PreparedStatement mockPreparedStmnt;
    @Mock private ResultSet mockResultSet;
    @Mock private Table mockTable;
    @Mock private Statement mockStmnt;

    @InjectMocks private DBManager sut = new DBManager();

    @Mock private Library mockLib;

    @Before
    public void setUp() throws SQLException {
        when(mockDataSource.openConnection()).thenReturn(mockConn);
        doNothing().when(mockConn).commit();
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
        when(mockPreparedStmnt.execute()).thenReturn(Boolean.TRUE);
        when(mockPreparedStmnt.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(mockStmnt.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockConn.createStatement()).thenReturn(mockStmnt);
    }

    @Test
    public void testLoadLibraries() throws SQLException{
        sut.loadLibraries(mockTable);

        verify(mockDataSource, times(1)).openConnection();
        verify(mockConn,times(1)).createStatement();
        verify(mockStmnt,times(1)).executeQuery(anyString());
        verify(mockResultSet,times(2)).next();
        verify(mockResultSet,times(5)).getString(anyString());
        verify(mockTable,times(1)).addData(any(Library.class));
        verify(mockDataSource, times(1)).close(mockConn);
    }

    @Test
    public void testAddLibraries() throws SQLException {
        sut.add("name","framework","language","Yes", "No");

        verify(mockDataSource, times(1)).openConnection();
        verify(mockConn,times(1)).prepareStatement(anyString());
        verify(mockPreparedStmnt, times(5)).setString(anyInt(), anyString());
        verify(mockPreparedStmnt, times(1)).executeUpdate();
        verify(mockDataSource, times(1)).close(mockConn);
    }

    @Test
    public void testDeleteLibraries() throws SQLException {
        sut.delete("name");

        verify(mockDataSource, times(1)).openConnection();
        verify(mockConn,times(1)).prepareStatement(anyString());
        verify(mockPreparedStmnt, times(1)).setString(anyInt(), anyString());
        verify(mockPreparedStmnt, times(1)).executeUpdate();
        verify(mockDataSource, times(1)).close(mockConn);
    }

    @Test
    public void testUpdateLibraries() throws SQLException {
        sut.update(mockLib,"name");

        verify(mockDataSource, times(1)).openConnection();
        verify(mockConn,times(1)).prepareStatement(anyString());
        verify(mockPreparedStmnt, times(6)).setString(anyInt(), anyString());
        verify(mockPreparedStmnt, times(1)).executeUpdate();
        verify(mockDataSource, times(1)).close(mockConn);
    }
}
