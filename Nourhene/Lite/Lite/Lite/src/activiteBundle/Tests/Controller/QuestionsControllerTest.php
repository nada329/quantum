<?php

namespace activiteBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class QuestionsControllerTest extends WebTestCase
{
    public function testAddquestion()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/addQuestion');
    }

    public function testListequestion()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/listeQuestion');
    }

    public function testUpdatequestion()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/updateQuestion');
    }

}
