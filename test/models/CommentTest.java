package models;

import org.junit.*;

/**
 * Unit tests for {@link Comment} domain object
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class CommentTest extends BaseDataUnitTest {

    @Test
    public void testCountByMember() {
        Member member = Member.all().first();
        final long count = Comment.countByMember(member);
        assertTrue(count >= 0);
    }

    @Test
    public void testRecentsByMember() {
        Member member = Member.all().first();
        assertNotNull(Comment.recentsByMember(member, 10));
    }

    @Test
    public void deleteForMember() {
        Member member = Member.all().first();
        assertNotNull(Comment.deleteForMember(member));
    }
}
