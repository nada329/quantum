<?php

namespace BibliothequeBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * LivreRating
 *
 * @ORM\Table(name="livre_rating")
 * @ORM\Entity(repositoryClass="BibliothequeBundle\Repository\LivreRatingRepository")
 */
class LivreRating
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
     * @var int
     *
     * @ORM\Column(name="rating", type="integer")
     */
    private $rating;

    /**
     * @ORM\ManyToOne(targetEntity="UserBundle\Entity\User")
     * @ORM\JoinColumn(name="user",referencedColumnName="id")
     */
    private $user;

    /**
     * @ORM\ManyToOne(targetEntity="Livre")
     * @ORM\JoinColumn(name="livre",referencedColumnName="id")
     */
    private $livre;


    public function getId()
    {
        return $this->id;
    }

    public function setRating($rating)
    {
        $this->rating = $rating;
        return $this;
    }

    public function getRating()
    {
        return $this->rating;
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

    public function setLivre($livre)
    {
        $this->livre = $livre;
        return $this;
    }

    public function getLivre()
    {
        return $this->livre;
    }
}

