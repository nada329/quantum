<?php

namespace AdministrationBundle\Tests\Controller;

use Symfony\Bundle\FrameworkBundle\Test\WebTestCase;

class EleveControllerTest extends WebTestCase
{
    public function testListel()
    {
        $client = static::createClient();

        $crawler = $client->request('GET', '/listel');
    }

}
