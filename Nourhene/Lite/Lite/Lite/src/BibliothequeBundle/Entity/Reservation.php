<?php

namespace BibliothequeBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Reservation
 *
 * @ORM\Table(name="reservation")
 * @ORM\Entity(repositoryClass="BibliothequeBundle\Repository\ReservationRepository")
 */
class Reservation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @ORM\ManyToOne(targetEntity="Livre")
     * @ORM\JoinColumn(name="id_Livre",referencedColumnName="id")
     */
    private $livre;

    /**
     * @ORM\ManyToOne(targetEntity="UserBundle\Entity\User")
     * @ORM\JoinColumn(name="id_User",referencedColumnName="id")
     */
    private $user;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="datetime")
     * @Assert\Date
     * @Assert\Range(
     *      min = "now",
     *      max = "+15 days"
     * )
     */
    private $date;


    /**
     * @ORM\Column(name="state", type="string")

     */
    private $state;

    /**
     * @var string
     *
     * @ORM\Column(name="duree", type="integer")
     * @Assert\Range(
     *      min = 1,
     *      max = 15
     * )
     */
    private $duree;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @return mixed
     */
    public function getState()
    {
        return $this->state;
    }

    /**
     * @param mixed $state
     */
    public function setState($state)
    {
        $this->state = $state;
    }



    public function setLivre($livre)
    {
        $this->livre = $livre;

        return $this;
    }

    public function getLivre()
    {
        return $this->livre;
    }


    public function setUser($user)
    {
        $this->user = $user;

        return $this;
    }


    public function getUser()
    {
        return $this->user;
    }


    public function setDate($date)
    {
        $this->date = $date;

        return $this;
    }


    public function getDate()
    {
        return $this->date;
    }


    public function setDuree($duree)
    {
        $this->duree = $duree;

        return $this;
    }


    public function getDuree()
    {
        return $this->duree;
    }
}

