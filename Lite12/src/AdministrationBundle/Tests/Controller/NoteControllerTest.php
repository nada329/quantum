<?php

namespace AdministrationBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class NoteControllerTest extends WebTestCase
{
    public function testCalcul()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/calcul');
    }

    public function testAff()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/aff');
    }

    public function testUpdate()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/update');
    }

}
